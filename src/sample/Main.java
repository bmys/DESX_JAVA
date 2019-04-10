package sample;

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
        System.out.println();
        // for(byte b: "c".getBytes()) System.out.print(b);
//         UtilityTest.runTest();

        byte[] key =
                       {0, 1, 0, 0, 0, 0, 0, 1,
                        0, 0, 0, 1, 0, 1, 0, 0,
                        0, 1, 0, 0, 0, 0, 0, 1,
                        0, 0, 0, 1, 0, 1, 0, 1,

                        0, 1, 0, 0, 0, 1, 0, 1,
                        0, 0, 0, 1, 0, 0, 0, 0,
                        0, 1, 0, 0, 0, 1, 0, 1,
                        0, 0, 0, 1, 0, 0, 1, 0,};

//        byte[] msg =
//                       {1, 1, 0, 1, 1, 0, 1, 1,
//                        0, 1, 0, 1, 0, 1, 1, 1,
//                        0, 1, 0, 1, 0, 1, 0, 1,
//                        1, 1, 1, 1, 0, 1, 0, 1,
//
//                        0, 1, 0, 1, 1, 1, 0, 1,
//                        0, 1, 1, 1, 0, 0, 0, 1,
//                        1, 1, 1, 1, 0, 1, 0, 1,
//                        0, 0, 0, 1, 0, 1, 0, 1,};
                byte[] msg =
                        {0,1,1,0,1,0,0,0,
                               0,1,1,0,0,1,0,1,
                               0,1,0,0,1,1,0,0,
                               0,0,1,0,1,1,0,0,

                               0,1,1,0,1,1,0,1,
                               0,0,1,0,0,1,0,0,
                               0,0,1,1,1,0,1,0,
                               0,0,1,0,1,0,0,1,};
//        byte[] msg =
//                        {1, 2, 3, 4, 5, 6, 7, 8,
//                        9, 10, 11, 12, 13, 14, 15, 16,
//                        17, 18, 19, 20, 21, 22, 23, 24,
//                        25, 26, 27, 28, 29, 30, 31, 32,
//
//                        33, 34, 35, 36, 37, 38, 39, 40,
//                        41, 42, 43, 44, 45, 46, 47, 48,
//                        49, 50, 51, 52, 53, 54, 55, 56,
//                        57, 58, 59, 60, 61, 62, 63, 64,};


        Des.Des des = new Des.Des();
        System.out.println("MSG:");
        printArr(msg);

        byte [] encrypted = des.encrypt(msg, key);
        System.out.println("ENCRYPTED:");
        printArr(encrypted);


        byte [] decrypted = des.encrypt(encrypted, key);
        System.out.println("DECRYPTED:");
        printArr(decrypted);

        // byte [] decrypted = des.encrypt(encrypted, key);




//


//        byte[] three = {0,1,0,1,1}
//        byte[] b = Utility.toByteArray(1);
//
//        for (int i = 0; i < 4; i++) {
//            System.out.println(b[i]);
//        }

//        byte[] k = {1,1,0,1, 0,1,0,1,
//                    2,1,0,1, 0,1,0,1,
//                    3,1,0,1, 0,1,0,1,
//                    4,1,0,1, 0,1,0,1,
//                    5,1,0,1, 0,1,0,1,
//                    6,1,0,1, 0,1,0,1,
//                    7,1,0,1, 0,1,0,1,
//                    8,1,0,1, 0,1,0,1};


//        launch(args);

//         Tests xDDDD
        byte[] cipher = strToArr("0010100111011000001000110011011100111100010101100100100111111011");
        byte[] cipher2 = Des.Des.initialPermutation(cipher);
        System.out.println("1110001010111010001110001100110110000010100111011101001110101100");
        System.out.println(arrToStr(cipher2));
////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nKey test");
        byte[] key1 = strToArr("1101000111010100101000110101110010101010000100011101010010101010");
        key1 = Des.Des.KeyPermutation(key1);
        System.out.println(arrToStr(key1));

        System.out.println("11010111010010111001010001101001010001001010100110001011");
////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nSplit arr test");
        byte[][] splited = Utility.splitArrayInHalf(key1);

        System.out.println(Arrays.equals(strToArr("1101011101001011100101000110"), splited[0]));
        System.out.println(Arrays.equals(strToArr("1001010001001010100110001011"), splited[1]));

////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nShift test");

        byte[] shiftedL =  Utility.shiftArray(splited[0],1);
        byte[] shiftedR =  Utility.shiftArray(splited[1],1);

        System.out.println(Arrays.equals(strToArr("1010111010010111001010001101"), shiftedL));
        System.out.println(Arrays.equals(strToArr("0010100010010101001100010111"), shiftedR));
////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nArray cat test");
        byte[] catArr = Utility.catArrays(shiftedL, shiftedR);
        System.out.println(Arrays.equals(strToArr("10101110100101110010100011010010100010010101001100010111"),
                catArr));
////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nCompression test");
        byte[] compressed = Des.Des.KeyCompression(catArr);
        System.out.println(Arrays.equals(strToArr("100011111110011010110000011111010011100100010000"),
                compressed));
////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nExtension test");
        byte[] ext = FFunction.extension(strToArr("10000010100111011101001110101100"));
        System.out.println(Arrays.equals(strToArr("010000000101010011111011111010100111110101011001"),
                ext));
////////////////////////////////////////////////////////////////////////////////////////
System.out.println("\nSBox test");

System.out.println(Arrays.equals(strToArr("1001"),
        SBlock.get(0,strToArr("011010"))));

System.out.println(Arrays.equals(strToArr("1100"),
        SBlock.get(0,strToArr("110010"))));

System.out.println(Arrays.equals(strToArr("1111"),
        SBlock.get(0,strToArr("000011"))));

////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nXOR test");
        byte[] xor = Utility.xorArrays(ext, compressed);
        System.out.println(Arrays.equals(strToArr("110011111011001001001011100101110100010001001001"),
                xor));
////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nSBox test");
        byte[] sbox = FFunction.sBox(xor);
        System.out.println(Arrays.equals(strToArr("10110101001111111100010011101010"),
                sbox));


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
       byte[] result = new byte[text.length()];
       int i = 0;
       for (char ch: text.toCharArray()){
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
}
