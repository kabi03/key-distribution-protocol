//Author: Kabilan Veerasingam 500558923
import java.util.Scanner;

public class Vigenere {


    public static String encode(String text, String key){
        String encoded = "";
        int text_letter_int, key_letter_int, encoded_letter_int;
        int index_of_key = 0;

        for (int i = 0; i<text.length(); i++){
            if (index_of_key == key.length()){
                index_of_key = 0;

            }

            if (text.charAt(i) == ' '){

                encoded  = encoded + ' ';
                continue;

            }
            text_letter_int = ((int)(text.charAt(i)))-65;
            key_letter_int = ((int)(key.charAt(index_of_key)))-65;
            encoded_letter_int =  key_letter_int + text_letter_int;

            if (encoded_letter_int > 25){
                encoded_letter_int = encoded_letter_int % 26;
            }



            encoded_letter_int = encoded_letter_int + 65;
            encoded = encoded + (char)encoded_letter_int;
            index_of_key++;
        }

        return encoded;

    }

    public static String decode(String encoded, String key){
        String decoded = "";

        int encoded_letter_int, key_letter_int, text_letter_int;
        int index_of_key = 0;

        for (int i = 0; i<encoded.length(); i++){
            if (index_of_key == key.length()){
                index_of_key = 0;

            }

            if (encoded.charAt(i) == ' '){

                decoded  = decoded + ' ';
                continue;

            }
            encoded_letter_int = ((int)(encoded.charAt(i)))-65;
            key_letter_int = ((int)(key.charAt(index_of_key)))-65;
            text_letter_int =  encoded_letter_int - key_letter_int;

            if (text_letter_int < 0){
                text_letter_int = text_letter_int + 26;
            }



            text_letter_int = text_letter_int + 65;
            decoded = decoded + (char)text_letter_int;
            index_of_key++;
        }

        return decoded;

    }
}
