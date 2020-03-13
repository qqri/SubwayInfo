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

    @GetMapping("/apitest")
    public String callapihttp() throws IOException {
        StringBuffer result = new StringBuffer();
        try {
            String urlstr = "http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getRouteAcctoSpcifySttnAccesBusLcInfo?serviceKey" +
                    "=" +
                    "&routeId=DJB30300052" +
                    "&nodeId=DJB8005621" +
                    "&cityCode=25"
                    ;

            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream() , "UTF-8"));

            String returnLine;
            result.append("<xmp>");

            while((returnLine = br.readLine()) != null ) {
                result.append(returnLine + "\n");
                result.append("\n");
            }

            urlConnection.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return  result+ "</xmp>";
    }
}