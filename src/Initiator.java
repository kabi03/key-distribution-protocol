//author: kabilan veerasingam 500558923
import java.net.*;
import java.io.*;
import java.util.*;

public class Initiator {


    public static void main (String[] args){
        try{
            String serverName;
            int port;
            String receive, send;
            Scanner reader = new Scanner(System.in);
            System.out.print("Please enter the server address: ");
            serverName = reader.nextLine();
            System.out.print("Please enter the server port number: ");
            port = Integer.valueOf(reader.nextLine());

            Socket client = new Socket(serverName, port);
            DataInputStream in =  new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            System.out.println(in.readUTF());
            System.out.print("Please enter the cleartext identity of A to send to server: ");
            String identity_A = reader.nextLine();
            send = identity_A;
            System.out.println("Sending message 1 to server: " + send);
            out.writeUTF(send);
            receive = in.readUTF();
            System.out.println("Received cyphertext of message 2 from server: " + receive);
            String key = in.readUTF();

            String decoded_text = Vigenere.decode(receive, key);
            System.out.println("The decrypted message of message 2: " + decoded_text);
            String identity_B = get_Identity_B(decoded_text);
            System.out.println("Before encoding message 3: " + identity_B);
            String new_key = get_Key(decoded_text);
            String encoded_identity_B = Vigenere.encode(identity_B, new_key);

            out.writeUTF(encoded_identity_B);
            System.out.println("Sending cypher text of message 3 to server: " + encoded_identity_B);
            out.writeUTF(new_key);



            client.close();





        }catch(IOException e){

            e.printStackTrace();
        }

    }

    public static String get_Identity_B(String text){
        String identity_B;
        int number_spaces;
        int number_of_spaces = 0;
        int index = 0;
        for(int i = text.length()-1; i>-1; i--){
            if (text.charAt(i) == ' '){

                number_of_spaces++;
            }

            if (number_of_spaces == 2){

                index = i+1;
                break;
            }



        }

        identity_B = text.substring(index, (text.length()));

        return identity_B;

    }


    public static String get_Key(String text){
        String key;


        int index = 0;
        for(int i = 0; i<text.length(); i++){
            if (text.charAt(i) == ' '){

                index = i;
                break;
            }





        }

        key = text.substring(0, index);

        return key;
    }


}
