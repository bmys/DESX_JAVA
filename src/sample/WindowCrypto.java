package sample;
import Des.DesX;
import Des.Utility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WindowCrypto {
    JFrame f;
    boolean plik = false;
    private String path;
    WindowCrypto(){
        f=new JFrame();//creating instance of JFrame

        JCheckBox check = new JCheckBox();
        check.setBounds(32,150,32, 32);
        JButton b=new JButton("Wybierz plik");
        JButton b2=new JButton("Szyfruj");
        JLabel nazwaPliku = new JLabel("Nazwa pliku");
        nazwaPliku.setBounds(165,50,250, 40);
        JTextField haslo = new JTextField();
        haslo.setBounds(165,100,250, 40);
        b.setBounds(15,50,100, 40);
        b2.setBounds(15,100,100, 40);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    path = selectedFile.getAbsolutePath();
                    nazwaPliku.setText(path);
                    plik = true;
                }


            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String pass = haslo.getText();
                byte[] pass_arr = Main.strToArr(pass);

                if(plik){
//                    System.out.println(path);
//                    System.out.println(Main.arrToStr(pass_arr));
                    fileUtils.encrypt(path, path+"encrypted",pass_arr, check.isSelected());
                }
            }
        });
        f.add(b);//adding button in JFrame
        f.add(b2);//adding button in JFrame
        f.add(haslo);
        f.add(nazwaPliku);
        f.add(check);
        f.setSize(850,250);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible


}
}
