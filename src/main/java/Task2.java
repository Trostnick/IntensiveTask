import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public static final String RESOURCE_FOLDER = "C:\\Users\\prokhorov\\IdeaProjects\\Task2\\src\\main\\resources";

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5555);

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("Oo, It's a request!!!!!!!!");
                GetRequest request = Utils.readRequestFromInputStream(socket.getInputStream());
                OutputStream responceOutputStream = socket.getOutputStream();
                File resourceFile = new File(RESOURCE_FOLDER + request.getResourcePath());
                byte[] responceBody = Utils.readInputStreamAsByteArray(new FileInputStream(resourceFile));
                Responce responce = new Responce(request.getHttpVersion(), "200 OK",
                        responceBody);
                responceOutputStream.write(responce.toBytes());


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
