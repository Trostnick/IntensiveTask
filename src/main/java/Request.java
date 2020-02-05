import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Request {

    private String method;
    private String resourceRelativePath;
    private String httpVersion;
    private Map<String, String> headers;
    private byte[] body;

    public Request(InputStream inputStream) throws IOException, BadRequestException {

        try {
            String firstLine = Utils.readLine(inputStream);
            String[] firstStringArray = firstLine.split(" ");
            this.method = firstStringArray[0];
            this.resourceRelativePath = firstStringArray[1];
            this.httpVersion = firstStringArray[2];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BadRequestException("Incorrect first row in request");
        }

        this.headers = new HashMap<>();
        String headerLine = Utils.readLine(inputStream);
        while(!headerLine.isEmpty() && !headerLine.equals("\r")){
            String[] currentHeader = headerLine.split(": ");
            this.headers.put(currentHeader[0], currentHeader[1]);
            headerLine = Utils.readLine(inputStream);
        }


        if (this.hasBody()){
            this.body = Utils.readInputStreamAsByteArray(inputStream);
        }

    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
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

    public boolean hasBody(){
        return this.method.equals("POST") || this.method.equals("PUT");
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.method)
                .append(Constants.SPACE_SYMBOL)
                .append(this.resourceRelativePath)
                .append(Constants.SPACE_SYMBOL)
                .append(this.httpVersion)
                .append(Constants.LINE_SEPARATOR);

        this.headers.forEach((key, value) -> {
            stringBuilder .append(key)
                    .append(Constants.HEADER_NAME_SEPARATOR)
                    .append(value)
                    .append(Constants.LINE_SEPARATOR);
        });
        stringBuilder.append(Constants.LINE_SEPARATOR);

        if (this.hasBody()){
            stringBuilder .append(Arrays.toString(this.body));
        }

        return stringBuilder.toString();

    }
}
