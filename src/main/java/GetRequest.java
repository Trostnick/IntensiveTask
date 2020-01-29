import java.util.Map;

public class GetRequest {

    private byte[] data;
    private String method;
    private String resourceRelativePath;
    private String httpVersion;
    private Map<String, String> headers;

    public GetRequest() {
    }

    public GetRequest(byte[] data, String method, String resource, String httpVersion, Map<String, String> headers) {
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
