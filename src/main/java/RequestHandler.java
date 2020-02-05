import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestHandler {

    public void handleGetRequestFromServerSocket(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();

            try (InputStream requestInputStream = socket.getInputStream();
                 OutputStream responseOutputStream = socket.getOutputStream()) {

                Request request = new Request(requestInputStream);
                MainClass.log.info(request.toString());
                File resourceFile = new File(Constants.RESOURCE_DIRECTORY_PATH + request.getResourceRelativePath());
                Response response = new Response(resourceFile, request.getHttpVersion());
                responseOutputStream.write(response.toBytes());
            } catch (IOException | BadRequestException ignored) {
                //TODO: сделать возврат статуса ошибки и страници ошибки
            }
        }
    }
}
