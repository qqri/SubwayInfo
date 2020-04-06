package com.qqri.whatsub.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

@RestController
public class WebRestController {

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @GetMapping("/linktest")
    public String callsubinfo() {
        return "";
    }
    @GetMapping("/apitest")
    public String callapihttp() throws IOException {
        StringBuffer result = new StringBuffer();
        try {
//            String urlstr = "http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getRouteAcctoSpcifySttnAccesBusLcInfo?serviceKey" +
//                    "=2Tc7YrqeAa%2Bt%2FmpbQLJNGj3GivNzGt5iFTo6X2Okpqy%2FGDUpxYaYAebWMDFtoxlZdLBQwabODZh6cAzAIplk6Q%3D%3D" +
//                    "&cityCode=25"+
//                    "&routeId=DJB30300052"
//                    ;
            String urlstr = "http://apis.data.go.kr/1741000/EarthquakeIndoors/getEarthquakeIndoorsList?serviceKey=2Tc7YrqeAa%2Bt%2FmpbQLJNGj3GivNzGt5iFTo6X2Okpqy%2FGDUpxYaYAebWMDFtoxlZdLBQwabODZh6cAzAIplk6Q%3D%3D&pageNo=1&numOfRows=10&type=xml&flag=Y";
            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream() , "UTF-8"));

            String returnLine;
            result.append("<xmp>");

            while((returnLine = br.readLine()) != null ) {
                result.append(returnLine);
                result.append("\n");
            }

            urlConnection.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return  result+ "</xmp>";
    }
}