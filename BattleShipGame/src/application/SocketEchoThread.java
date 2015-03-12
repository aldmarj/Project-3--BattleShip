package application;


import java.net.*;
import java.io.*;

public class SocketEchoThread extends Thread {
    private Socket socket;
    public SocketEchoThread(Socket socket, Main gui) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader responses = 
                new BufferedReader
                (new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("Connection open.");
            writer.println("I will echo a single message, then close.");

            StringBuilder sb = new StringBuilder();
            while (!responses.ready()){}
            while (responses.ready()) {
                sb.append(responses.readLine() + '\n');
            }
            System.out.println("From: " + socket.getInetAddress() + ": " + sb);
        
            writer.print(sb);
            writer.flush();
            //updateText.updateText(sb, updateText);
            socket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } 
    }
}
