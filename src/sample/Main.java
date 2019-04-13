package sample;

import Des.DesX;
import Des.FFunction;
import Des.SBlock;
import Des.Utility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import test.UtilityTest;

import java.util.Arrays;

//extends Application
public class Main {

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }


    public static void main(String[] args) {
        Des.Des des = new Des.Des();
        String msg_text = "0010100111011000001000110011011100111100010101100100100111111011";
        String key_text =  "1101000111010100101000110101110010101010000100011101010010101010";

        byte [] msg = strToArr(msg_text);
        byte [] key = strToArr(key_text);

        System.out.print("Message:   ");
        System.out.println(msg_text);

        byte [] encrypted = des.encrypt(msg, key, false);
        System.out.print("Encrypted: ");
        System.out.println(arrToStr(encrypted));

        byte [] decrypted = des.encrypt(encrypted, key, true);
        System.out.print("Decrypted: ");
        System.out.println(arrToStr(decrypted));
    }

    public static void printArr(byte[]arr){
        int c = 0;
        for (byte el : arr) {
            System.out.print(el);
            System.out.print(' ');
            if (c == 7) {
                System.out.println();
                c = 0;
            } else {
                c++;
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

     static byte[] strToArr(String text){
        text = text.replaceAll("\\s+", "");
       byte[] result = new byte[text.length()];
       int i = 0;
       for (char ch: text.toCharArray()){
//           if(ch == ' ') continue;
           if(ch == '0'){
               result[i] = 0;
           }
           else {
               result[i] = 1;
           }
           i++;
       }
       return result;
    }

    static String arrToStr(byte[] bytes){
        StringBuilder result = new StringBuilder();

        for (byte b : bytes){
            if(b == 1){
                result.append("1");
            }
            else{
                result.append("0");
            }
        }
        return result.toString();
    }

    public static String AsciiToBinary(String asciiString){

        byte[] bytes = asciiString.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            // binary.append(' ');
        }
        return binary.toString();
    }
}
