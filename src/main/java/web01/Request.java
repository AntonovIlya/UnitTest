package web01;

import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

    private String requestMethod;
    private String requestedURL;
    private URIBuilder builder;
    private String protocolVersion;
    private final Map<String, String> bodyHeaders = new HashMap<>();
    private final StringBuilder messageBody = new StringBuilder();
    private final Map<String, List<String>> postParams = new HashMap<>();
    private final BufferedReader data;
    private final List<Integer> listOfCodes = new ArrayList<>();

    public Request(BufferedReader data) throws IOException, URISyntaxException {
        this.data = data;
        parseRequest();
    }

    public void parseRequest() throws IOException, URISyntaxException {
        StringBuilder sb = new StringBuilder();
        String[] requestLine = data.readLine().split(" ");
        requestMethod = requestLine[0];
        requestedURL = requestLine[1];
        builder = new URIBuilder(requestedURL);
        protocolVersion = requestLine[2];
        while (data.ready()) {
            int symbol = data.read();
            listOfCodes.add(symbol);
        }
        int listSize = listOfCodes.size();
        for (int i = 0; i < listSize; i++) {
            char ch = (char) listOfCodes.get(i).intValue();
            if (listOfCodes.get(i) == 13 && listOfCodes.get(i + 1) == 10 && listOfCodes.get(i - 1) != 10) {
                int index = sb.indexOf(" ");
                bodyHeaders.put(sb.substring(0, index - 1), sb.substring(index + 1));
                sb = new StringBuilder();
                i++;
                continue;
            } else if (listOfCodes.get(i) == 13 && listOfCodes.get(i + 1) == 10 && listOfCodes.get(i - 1) == 10) {
                if (i + 1 == listSize) break;
                for (int j = i + 2; j < listSize; j++) {
                    messageBody.append((char) listOfCodes.get(j).intValue());
                }
                break;
            }
            sb.append(ch);
        }
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestedURL() {
        return requestedURL;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public Map<String, String> getBodyHeaders() {
        return bodyHeaders;
    }

    public String getMessageBody() {
        return messageBody.toString();
    }

    public String getPath() {
        return builder.getPath();
    }

    public List<String> getPostParam(String name) {
        return postParams.get(name);
    }

    public Map<String, List<String>> getPostParams() {
        if (!(bodyHeaders.containsKey("Content-Type") && bodyHeaders.get("Content-Type").equals("application/x-www-form-urlencoded"))) return postParams;
        String[] pairs = messageBody.toString().split("&");
        for (String s : pairs) {
            String[] nameValue = s.split("=");
            String value = URLDecoder.decode(nameValue[1], StandardCharsets.UTF_8);
            String name = nameValue[0];
            if (postParams.containsKey(name)) {
                postParams.get(name).add(value);
                continue;
            }
            List<String> list = new ArrayList<>();
            list.add(value);
            postParams.put(name, list);
        }
        return postParams;
    }
}
