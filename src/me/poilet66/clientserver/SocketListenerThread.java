package me.poilet66.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketListenerThread extends Thread{

    private ServerSocketsManager manager;
    private int id;
    private Socket socket;
    private boolean interrupted;

    public SocketListenerThread(int id, Socket socket, ServerSocketsManager manager) {
        this.id = id;
        this.socket = socket;
        this.manager = manager;
        interrupted = false;
    }

    public void halt() {
        this.interrupted = true;
    }

    public void run() {
        try {

            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(in);

            PrintWriter pw;

            String data;

            System.out.println(String.format("Listen thread for client %s started.", id));

            do{
                data = br.readLine();
                System.out.println(data);
                manager.messageAllClients(data);

            } while(!interrupted);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
