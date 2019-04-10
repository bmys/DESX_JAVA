package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import test.UtilityTest;

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
                       {0, 1, 0, 1, 0, 1, 0, 1,
                        0, 0, 0, 1, 0, 1, 0, 1,
                        0, 1, 0, 1, 0, 1, 0, 1,
                        0, 0, 0, 1, 0, 1, 0, 1,

                        0, 1, 0, 1, 0, 1, 0, 1,
                        0, 1, 0, 1, 0, 0, 0, 1,
                        0, 1, 0, 0, 0, 1, 0, 1,
                        0, 1, 0, 1, 0, 1, 1, 1,};

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
                               0,1,1,0,1,1,0,0,
                               0,1,1,0,1,1,0,0,

                               0,1,1,0,1,1,1,1,
                               0,0,1,0,0,0,0,0,
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


        Des.Des des = new Des.Des(key);

        int c = 0;
        for (byte el : des.encrypt(msg, key)) {
            System.out.print(el);
            System.out.print(' ');
            if (c == 7) {
                System.out.println();
                c = 0;
            } else {
                c++;
            }
        }


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
    }
}
