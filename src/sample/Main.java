package sample;

import Des.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Des des = new Des();
        String msg_text = "0010100111011000001000110011011100111100010101100100100111111011";
        String key_text = "1101000111010100101000110101110010101010000100011101010010101010";

        byte[] msg = strToArr(msg_text);
        byte[] key = strToArr(key_text);
        key = Des.prepareKey(key);

        System.out.print("Key: ");
        System.out.println(arrToStr(key));

        System.out.print("Message:   ");
        System.out.println(msg_text);

        byte[] encrypted = des.encrypt(msg, key, false);
        System.out.print("Encrypted: ");
        System.out.println(arrToStr(encrypted));

        byte[] decrypted = des.encrypt(encrypted, key, true);
        System.out.print("Decrypted: ");
        System.out.println(arrToStr(decrypted));

        System.out.println(System.lineSeparator());

//        DES-X
        DesX desx = new DesX();

        String xKey_L = "1111111111011000111011111101101110100101100110101000100110000000";
        String xKey_R = "0101001111110100011110111101001001110111010001000110100100001110";
        String realKey = "11010111010010111001010001101001010001001010100110001011";

        String xKey_text = "1101000111010100101000110101110010101010000100011101010010101010";
        byte[] xKey = strToArr(xKey_L + xKey_text + xKey_R);

        System.out.print("Message:        ");
        System.out.println(msg_text);

        encrypted = desx.encrypt(msg, xKey, false);
        System.out.print("Encrypted DesX: ");
        System.out.println(arrToStr(encrypted));

        byte[] xKey2 = strToArr(xKey_R + xKey_text + xKey_L);


        decrypted = desx.encrypt(encrypted, xKey, true);
        System.out.print("Decrypted DesX: ");
        System.out.println(arrToStr(decrypted));

        fileUtils.encrypt("/home/arch/IdeaProjects/crypto/src/test/g.bmp", "/home/arch/IdeaProjects/crypto/src/test/g.bmp.crypted",
                xKey, false);
        fileUtils.encrypt("/home/arch/IdeaProjects/crypto/src/test/g.bmp.crypted", "/home/arch/IdeaProjects/crypto/src/test/nowe.bmp",
                xKey, true);

//        for (byte[] f: readFileToBytes("/home/arch/IdeaProjects/crypto/src/test/tajny.txt")){
//            System.out.println(arrToStr(f));
//        }

//        try (FileOutputStream fos = new FileOutputStream("/home/arch/IdeaProjects/crypto/src/test/crypt.bmp")) {
//            for (byte[] f: readFileToBytes("/home/arch/IdeaProjects/crypto/src/test/g.bmp")){
////                System.out.println(arrToStr(f));
//                fos.write(f);
//
//            }


        //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream


//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }


//    end main

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
