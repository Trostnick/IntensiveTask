import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Task2 {

    private static final String RESOURCE_FOLDER = "D:\\IdeaProject\\TechLeaders\\src\\main\\resources";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5555);) {
            handleGetRequestFromServerSocket(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleGetRequestFromServerSocket(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();

            try (InputStream requestInputStream = socket.getInputStream();
                 OutputStream responseOutputStream = socket.getOutputStream()) {

                GetRequest getRequest = Utils.readRequestFromInputStream(requestInputStream);
                File resourceFile = new File(RESOURCE_FOLDER + getRequest.getResourceRelativePath());

                byte[] responseBody;
                try (InputStream resourceFileInputStream = new FileInputStream(resourceFile);) {
                    responseBody = Utils.readInputStreamAsByteArray(resourceFileInputStream);
                }

                Response response = new Response(getRequest.getHttpVersion(), "200 OK", responseBody);
                responseOutputStream.write(response.toBytes());
            }
        }
    }
}
