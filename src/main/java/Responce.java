import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Responce {

    private String httpVersion;
    private String status;
    private Map<String, String> headers;
    private byte[] body;

    public Responce(String httpVersion, String status, byte[] body) {
        this.httpVersion = httpVersion;
        this.status = status;
        this.body = body;
        this.generateDefultHeaders();
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    private void generateDefultHeaders() {
        Map<String, String> defaultHeaders =new HashMap<>();
        defaultHeaders.put("Date", LocalDate.now().toString());
        defaultHeaders.put("Server", "MyFavoriteServer");
        defaultHeaders.put("LastModified", LocalDate.MIN.toString());
        defaultHeaders.put("Content-Length", String.valueOf(this.body.length));
        defaultHeaders.put("Content-Type", "text/html");
        defaultHeaders.put("Connection", "Closed");
        this.setHeaders(defaultHeaders);
    }

    public byte[] toBytes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.httpVersion).append(" ");
        stringBuilder.append(this.status);


        for (String key : this.getHeaders().keySet()) {
            stringBuilder.append("\n").append(key).append(": ").append(this.getHeaders().get(key));
        }

        stringBuilder.append("\r\n\r\n");
        stringBuilder.append(Arrays.toString(this.body));
        return stringBuilder.toString().getBytes();
    }


}
