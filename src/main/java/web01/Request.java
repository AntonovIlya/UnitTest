package web01;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
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

    public String getQueryParam(String name) {
        for (NameValuePair nameValuePair : getQueryParams()) {
            if (nameValuePair.getName().equals(name)) return nameValuePair.getValue();
        }
        return null;
    }

    public List<NameValuePair> getQueryParams() {
        return builder.getQueryParams();
    }

    public String getPath() {
        return builder.getPath();
    }

    public String getFragment() {
        return builder.getFragment();
    }
}
