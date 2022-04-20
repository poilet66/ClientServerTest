package me.poilet66.clientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PoiClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 4999);

        System.out.println("Connected to server!");

        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        Scanner scan = new Scanner(System.in);

        String message;

        while (true) {
            message = scan.nextLine();
            pw.println(message);
            pw.flush();
            System.out.println("Message sent!");
        }
    }



}
