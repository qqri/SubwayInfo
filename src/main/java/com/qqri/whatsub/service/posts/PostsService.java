package com.qqri.whatsub.service.posts;

import com.qqri.whatsub.domain.posts.Posts;
import com.qqri.whatsub.domain.posts.PostsRepository;
import com.qqri.whatsub.web.dto.PostsListResponseDto;
import com.qqri.whatsub.web.dto.PostsResponseDto;
import com.qqri.whatsub.web.dto.PostsSaveRequestDto;
import com.qqri.whatsub.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private WebDriver driver;
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "src/main/resources/chromedriver.exe";

    @PostConstruct
    public void test() {
       // Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");  // 현재 package의
        Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");  // 현재 package의

        // WebDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", path.toString());

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함
        //options.addArguments("--headless");
        // WebDriver 객체 생성
        ChromeDriver driver = new ChromeDriver( options );

        // 빈 탭 생성

        driver.get("https://finance.yahoo.com/quote/AAPL/history?p=AAPL");
        //driver.get("https://heodolf.tistory.com/101");
        for(int i = 0 ; i < 3 ; i++ ) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2000)");
            // 첫번째 탭으로 전환
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        WebElement page1_title = driver.findElementByXPath("//*[@id=\"content\"]/div[1]/div[1]/div/h1");
//        if( page1_title != null  ) {
//            System.out.println( page1_title.getText() );
//        }
        driver.close();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // WebDriver 종료
            driver.quit();
        }
    }



    public void getKoreaCovidDatas() throws IOException {
        String STOCK_DATAS_URL = "https://finance.yahoo.com/quote/GOOG/history";
        Document doc = Jsoup.connect(STOCK_DATAS_URL).get();
        Elements contents = doc.select("div table tbody tr");
        //1. 이중 포문으로 검사하는 경우
        Elements sellContent, buyContent ;
        int count = 20;
        float temp =0 , tempMin,tempLow, maxProfit = 0;
        String tempLowDate = "" , MinDate ="", MaxDate="";

        for(int i =0 ; i < count-1 ; i++) {
            sellContent = contents.get(i).select("td");
            temp = Float.parseFloat(sellContent.get(2).text().replace(",", ""));
            tempMin = 98765432;

            for (int j = i + 1; j < count; j++) {
                buyContent = contents.get(j).select("td");
                tempLow = Float.parseFloat(buyContent.get(3).text().replace(",", ""));
                if (tempLow > temp) continue;
                if (tempMin > tempLow) {
                    tempMin = tempLow;
                    tempLowDate = buyContent.get(0).text();
                }
            }
        }

        System.out.println("buy date : " + tempLowDate);
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
    * update 기능에서 데이터베이스에 쿼리 날리는 부분이 존재하지 않는다.
    * 이는 JPA의 영속성 컨텍스트 때문이다.
    * 영속성 컨텍스트란? 엔티티를 영구 저장하는 환경이다.
    *
    * 트랜잭션 안에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
    *
    * 트랜잭션이 끝나는 시점에서 해당 테이블에 변경분을 반영한다.
    * entity 객체의 값만 변경하면 별도록  update쿼리를 날릴 필요가 없는 것이다.
    * */
    @Transactional
    public Long update(Long id , PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        posts.update(requestDto.getDay(),requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto :: new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id) //JPA에서 지원중인 메소드
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts);
    }
}
