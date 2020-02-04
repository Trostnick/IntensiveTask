import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private Utils(){}

    public static byte[] readInputStreamAsByteArray(InputStream inputStream) throws IOException {
        int bytesCount = inputStream.available();
        byte[] result = new byte[bytesCount];
        int errorCode = inputStream.read(result);
        if (errorCode == -1) throw new IOException("Error reading file");
        return result;
    }

}
