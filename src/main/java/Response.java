import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Response {

    private String httpVersion;
    private String status;
    private Map<String, String> headers;
    private byte[] body;

    public Response(File resourceFile, Request request) throws IOException {
        try (InputStream resourceFileInputStream = new FileInputStream(resourceFile)) {
            this.body = Utils.readInputStreamAsByteArray(resourceFileInputStream);
        }

        this.httpVersion = request.getHttpVersion();
        this.status = Constants.HTTP_STATUS_OK;
        this.generateDefaultHeaders();
        if (request.getHeaders().containsKey(Constants.COOKIE_HEADER)) {
            HashMap<String, String> cookie = Utils.convertCookieStringToMap(request.getHeaders().get(Constants.COOKIE_HEADER));
            if (Cookies.isNewUser(UUID.fromString(cookie.get(Constants.USER_ID_STRING)))) {
                MainClass.log.info(Constants.NEW_USER_MESSAGE);
                addSetCookieHeader(Cookies.addCookie(Constants.DEFAULT_LIFE_TIME_COOKIE), Constants.DEFAULT_LIFE_TIME_COOKIE);
            } else {
                MainClass.log.info(Constants.FAMILIAR_USER_MESSAGE);
            }
        }
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
        defaultHeaders.put(Constants.DATE_HEADER, LocalDate.now().toString());
        defaultHeaders.put(Constants.CONTENT_LENGTH_HEADER, String.valueOf(this.body.length));
        defaultHeaders.put(Constants.CONTENT_TYPE_HEADER, Constants.TEXT_HTML_CHARSET_UTF_8_HEADER);
        defaultHeaders.put(Constants.CONNECTION_HEADER, Constants.CLOSED_HEADER);
        this.setHeaders(defaultHeaders);
    }

    private void addSetCookieHeader(UUID cookie, long lifeTime) {
        String cookieHeaderValue = Constants.USER_ID_STRING +
                Constants.HEADER_VALUE_PART_SEPARATOR +
                cookie.toString() +
                Constants.HEADER_VALUE_SEPARATOR +
                Constants.LIFE_TIME_COOKIE_KEY +
                Constants.HEADER_VALUE_PART_SEPARATOR +
                lifeTime;
        this.headers.put(Constants.SET_COOKIE_HEADER, cookieHeaderValue);
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
