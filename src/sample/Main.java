package sample;

import Des.Utility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {

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
    byte[] k = {1,2,3,4,5};
        k = Utility.shiftArray(k,2);

        for(byte i: k){
            System.out.println(i);
        }



//        launch(args);
    }
}
