import java.io.*;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MainClass {

    public static Logger log = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {

        try {
            LogManager.getLogManager().readConfiguration(
                    MainClass.class.getResourceAsStream(Constants.RESOURCE_DIRECTORY_PATH
                            + Constants.LOGGING_PROPERTY_FILE_NAME));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

        try (ServerSocket serverSocket = new ServerSocket(5555);) {
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.handleGetRequestFromServerSocket(serverSocket);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception:", e);
        }
    }
}
