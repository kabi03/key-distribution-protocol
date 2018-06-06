//author: kabilan veerasingam 500558923
import java.net.*;
import java.io.*;
import java.util.*;

public class Responder extends Thread{

    private ServerSocket socket_server;

    public Responder(){
        try {
            socket_server = new ServerSocket(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run(){


            try{
                String receive, send;
                Scanner reader = new Scanner(System.in);
                System.out.println("Client must connect to localhost on port " + socket_server.getLocalPort());
                Socket server = socket_server.accept();

                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Server Message: You are connected to " + server.getLocalSocketAddress());
                receive = in.readUTF();
                System.out.println("Received message 1 from client: " + receive);
                String identity_A = receive;
                System.out.print("Enter the key: ");
                String key = reader.nextLine();
                System.out.print("Please enter the cleartext identity of B: ");
                String identity_B = reader.nextLine();
                System.out.print("Please enter the session key: ");
                String sess_key = reader.nextLine();
                String cleartext = sess_key + " " + identity_A + " " + identity_B;
                String encoded = Vigenere.encode(cleartext, key);
                System.out.println("Sending the cyphertext of message 2 to client: " + encoded);
                send = encoded;
                out.writeUTF(send);
                send = key;
                out.writeUTF(send);
                receive= in.readUTF();
                key = in.readUTF();
                System.out.println("Received cyphertext of message 3 from client: " + receive);
                String decoded_text = Vigenere.decode(receive, key);
                System.out.println("The decrypted message of message 3: " + decoded_text);
                server.close();

            }catch(IOException e){
                e.printStackTrace();

            }

    }

    public static void main(String[] args){
        Responder respond_server = new Responder();
        respond_server.start();


    }
}
