package me.poilet66.clientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketsManager {

    private List<Socket> sockets;
    private ServerSocket serverSocket;

    public ServerSocketsManager() {
        this.sockets = new ArrayList<>();
        try {
            this.serverSocket = new ServerSocket(4999);
            waitForConnections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForConnections() throws IOException {
        while (true) {
            Socket newSocket = serverSocket.accept();
            sockets.add(newSocket);
            PrintWriter pw = new PrintWriter(newSocket.getOutputStream(), true);
            pw.println("hello, you connected");

            System.out.println("New client connected");
            new SocketListenerThread(sockets.size(), newSocket, this).start();
        }
    }

    public List<Socket> getConnectedClients() {
        return this.sockets;
    }

    public void messageAllClients(String msg) throws IOException{
        for(Socket client : sockets) {
            PrintWriter pw = new PrintWriter(client.getOutputStream());
            pw.println(msg);
            pw.flush();
        }
    }

}
