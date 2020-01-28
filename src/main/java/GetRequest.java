import java.util.Map;

public class GetRequest {

    private byte[] data;
    private String method;
    private String resourcePath;
    private String httpVersion;
    private Map<String, String> headers;

    public GetRequest() {
    }

    public GetRequest(byte[] data, String method, String resource, String httpVersion, Map<String, String> headers) {
        this.data = data;
        this.method = method;
        this.resourcePath = resource;
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

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
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
