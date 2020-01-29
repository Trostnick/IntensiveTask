import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Task2 {


    private static final String RESOURCE_FOLDER = "D:\\IdeaProject\\TechLeaders\\src\\main\\resources";

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5555);) {
            handleGetRequestFromServerSocket(serverSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handleGetRequestFromServerSocket(ServerSocket serverSocket) throws IOException{
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Oo, It's a request!!!!!!!!");

            GetRequest request = Utils.readRequestFromInputStream(socket.getInputStream());
            OutputStream responceOutputStream = socket.getOutputStream();
            File resourceFile = new File(RESOURCE_FOLDER + request.getResourceRelativePath());
            byte[] responceBody = Utils.readInputStreamAsByteArray(new FileInputStream(resourceFile));
            Responce responce = new Responce(request.getHttpVersion(), "200 OK",
                    responceBody);
            responceOutputStream.write(responce.toBytes());


        }
    }
}
