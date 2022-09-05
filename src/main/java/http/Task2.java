package http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Task2 {

    public static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod?api_key=9se3cyO5Mi0VK4EPLSSIwu7oOb5QCgiQdFhJ6CA2";

    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("My Test Setvice")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(5000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);

        NasaResponse nasaResponse = Task2.mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });

        String url  = nasaResponse.getUrl();
        String name = parseName(url);

        CloseableHttpResponse response1 = httpClient.execute(new HttpGet(url));
        OutputStream os = new FileOutputStream(name + ".jpeg");
        response1.getEntity().writeTo(os);

    }

    public static String parseName(String url) {
        StringBuilder stringBuilder = new StringBuilder(url);
        int startIndex = stringBuilder.lastIndexOf("/");
        int endIndex = stringBuilder.indexOf(".jpg");
        return stringBuilder.substring(startIndex + 1, endIndex);
    }
}
