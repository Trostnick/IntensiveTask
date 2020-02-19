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

    public static String readLine ( InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int nextSymbol = inputStream.read();
        while (nextSymbol != -1 && nextSymbol!=Constants.LINE_SEPARATOR) {
            stringBuilder.append((char)nextSymbol);
            nextSymbol = inputStream.read();
        }
        return stringBuilder.toString();
    }

    public static HashMap<String, String> convertCookieStringToMap(String cookieString){
        HashMap<String, String> cookieMap = new HashMap<>();
        for (String cookiePart : cookieString.split(Constants.HEADER_VALUE_SEPARATOR)){
            String[] cookiePartArray = cookiePart.split(Constants.HEADER_VALUE_PART_SEPARATOR);
            cookieMap.put(cookiePartArray[0].trim(), cookiePartArray[1].trim());
        }
        return cookieMap;
    }

}
