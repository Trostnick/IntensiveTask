import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestHandler {

    public  void handleGetRequestFromServerSocket(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();

            try (InputStream requestInputStream = socket.getInputStream();
                 OutputStream responseOutputStream = socket.getOutputStream()) {


                GetRequest getRequest = Utils.readRequestFromInputStream(requestInputStream);
                File resourceFile = new File(Constants.RESOURCE_FOLDER + getRequest.getResourceRelativePath());

                byte[] responseBody;
                try (InputStream resourceFileInputStream = new FileInputStream(resourceFile);) {
                    responseBody = Utils.readInputStreamAsByteArray(resourceFileInputStream);
                }

                Response response = new Response(getRequest.getHttpVersion(), "200 OK", responseBody);
                responseOutputStream.write(response.toBytes());
            } catch (IOException | BadRequestException ignored)   {
                //TODO: сделать возврат статуса ошибки и страници ошибки
            }
        }
    }
}
