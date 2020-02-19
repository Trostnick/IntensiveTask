public class Constants {

    public static final String RESOURCE_DIRECTORY_PATH = System.getProperty("user.dir") + "/src/main/java/resources/";
    public static final String LOGGING_PROPERTY_FILE_NAME = "logging.properties";
    public static final String HEADER_NAME_SEPARATOR = ": ";
    public static final int LINE_SEPARATOR = '\n';
    public static final int SPACE_SYMBOL = ' ';
    public static final String HEADER_VALUE_SEPARATOR = ";";
    public static final String HEADER_VALUE_PART_SEPARATOR = "=";
    public static final String USER_ID_STRING = "user_id";

    public static final String HTTP_STATUS_OK = "200 OK";
    public static final String DATE_HEADER = "Date";
    public static final String CONTENT_LENGTH_HEADER = "Content-Length";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String TEXT_HTML_CHARSET_UTF_8_HEADER = "text/html; charset=utf-8";
    public static final String CONNECTION_HEADER = "Connection";
    public static final String CLOSED_HEADER = "Closed";
    public static final String SET_COOKIE_HEADER = "Set-Cookie";
    public static final String COOKIE_HEADER = "Cookie";
    public static final String LIFE_TIME_COOKIE_KEY = "expires";

    public static final long DEFAULT_LIFE_TIME_COOKIE = 60;

    public static final String NEW_USER_MESSAGE = "It's a new User";
    public static final String FAMILIAR_USER_MESSAGE = "It's a familiar User";

    private Constants() {
    }

}
