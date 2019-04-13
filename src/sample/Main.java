package sample;

import Des.*;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
import javax.swing.*;

import test.UtilityTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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
//        launch(args);

        new WindowCrypto();

    }

    public static List<byte[]> readFileToBytes(String path){
        List<byte[]> result = new LinkedList<>();

        try {
            byte[] array = Files.readAllBytes(new File(path).toPath());
            StringBuilder file = new StringBuilder();
            for(byte bt: array){
                file.append(Integer.toBinaryString(bt));
            }
            String output = file.toString();
            int len = output.length();
            int padd = 64 - (len % 64);

//            for (int i = 0; i < padd; i++) {
//                output += '0';
//            }

            for (String o: splitStringBySize(output, 64)){
                result.add(strToArr(o));
            }
            return result;
        }
        catch (IOException e) { e.printStackTrace(); }

        return result;
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

    private static Collection<String> splitStringBySize(String str, int size) {
        ArrayList<String> split = new ArrayList<>();
        for (int i = 0; i <= str.length() / size; i++) {
            split.add(str.substring(i * size, Math.min((i + 1) * size, str.length())));
        }
        return split;
    }
}
