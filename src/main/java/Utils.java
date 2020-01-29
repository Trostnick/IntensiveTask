import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static GetRequest readRequestFromInputStream(InputStream inputStream) throws IOException {
        GetRequest getRequest = new GetRequest();
        byte[] requestBytes = readInputStreamAsByteArray(inputStream);
        String requestString = new String(requestBytes);

        String[] requestArray = requestString.trim().split("\r\n");

        String[] firstRequestString = requestArray[0].split(" ");
        getRequest.setMethod(firstRequestString[0]);
        getRequest.setResourceRelativePath(firstRequestString[1]);
        getRequest.setHttpVersion(firstRequestString[2]);

        Map<String, String> headers = new HashMap<>();
        String[] currentHeader;
        for (int i = 1; i < requestArray.length; i++) {
            currentHeader = requestArray[i].split(": ");
            headers.put(currentHeader[0], currentHeader[1]);
        }
        getRequest.setHeaders(headers);
        return getRequest;
    }

    public static byte[] readInputStreamAsByteArray(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int bytesCount = inputStream.available();
        byte[] result = new byte[bytesCount];
        int errorCode = bufferedInputStream.read(result);
        if (errorCode == -1) throw new IOException("Error reading file");
        return result;
    }


}
