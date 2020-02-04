import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Request {

    private byte[] data;
    private String method;
    private String resourceRelativePath;
    private String httpVersion;
    private Map<String, String> headers;

    public Request(InputStream inputStream) throws IOException, BadRequestException {

        byte[] requestBytes = Utils.readInputStreamAsByteArray(inputStream);
        String requestString = new String(requestBytes);
        String[] requestArray = requestString.trim().split("\r\n");
        String[] firstRequestString = requestArray[0].split(" ");
        this.method = firstRequestString[0];

        try {
            this.resourceRelativePath = firstRequestString[1];
            this.httpVersion = firstRequestString[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BadRequestException("Incorrect first row in request");
        }

        Map<String, String> headers = new HashMap<>();
        for (int i = 1; i < requestArray.length; i++) {
            String[] currentHeader = requestArray[i].split(": ");
            headers.put(currentHeader[0], currentHeader[1]);
        }
        this.headers = headers;
    }

    public Request() {
    }

    public Request(byte[] data, String method, String resource, String httpVersion, Map<String, String> headers) {
        this.data = data;
        this.method = method;
        this.resourceRelativePath = resource;
        this.httpVersion = httpVersion;
        this.headers = headers;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResourceRelativePath() {
        return resourceRelativePath;
    }

    public void setResourceRelativePath(String resourceRelativePath) {
        this.resourceRelativePath = resourceRelativePath;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
