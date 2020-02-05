import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private String httpVersion;
    private String status;
    private Map<String, String> headers;
    private byte[] body;

    public Response(File resourceFile, String httpVersion) throws IOException {
        try (InputStream resourceFileInputStream = new FileInputStream(resourceFile)) {
            this.body = Utils.readInputStreamAsByteArray(resourceFileInputStream);
        }

        this.httpVersion = httpVersion;
        this.status = Constants.HTTP_STATUS_OK;
        this.generateDefaultHeaders();
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

    private void generateDefaultHeaders() {
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put(Constants.DATE, LocalDate.now().toString());
        defaultHeaders.put(Constants.CONTENT_LENGTH, String.valueOf(this.body.length));
        defaultHeaders.put(Constants.CONTENT_TYPE, Constants.TEXT_HTML_CHARSET_UTF_8);
        defaultHeaders.put(Constants.CONNECTION, Constants.CLOSED);
        this.setHeaders(defaultHeaders);
    }

    public byte[] toBytes() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.httpVersion).append(" ");
        stringBuilder.append(this.status);


        for (String key : this.getHeaders().keySet()) {
            stringBuilder.append("\n").append(key).append(": ").append(this.getHeaders().get(key));
        }

        stringBuilder.append("\r\n\r\n");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(stringBuilder.toString().getBytes());
        byteArrayOutputStream.write(this.body);

        return byteArrayOutputStream.toByteArray();
    }
}
