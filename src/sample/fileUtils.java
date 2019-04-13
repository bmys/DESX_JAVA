package sample;

import Des.DesX;

import java.io.*;

public class fileUtils {
    public static void encrypt(String path, String outPath,  byte[] key, boolean encrypt){

        System.out.println("Reading binary file into byte array example");
        try {
            //Instantiate the file object
            File file = new File(path);
            //Instantiate the input stread
            InputStream insputStream = new FileInputStream(file);
            long length = file.length();


            long padd = 64L - (length % 64L);
            // + (int)padd
            byte[] bytes = new byte[(int) length + (int)padd];



            insputStream.read(bytes);
            insputStream.close();

            for (int i = 0; i < padd; i++) {
                bytes[(int) length + i] = 0;
            }

            try (FileOutputStream fos = new FileOutputStream(outPath)) {
                DesX desx = new DesX();

                for (int i = 0; i < bytes.length / 64; i++) {
//                    System.out.println(i);
                    byte[] chunk = new byte[64];
                    System.arraycopy(bytes, i*64, chunk, 0, 64);
                    chunk = desx.encrypt(chunk, key, encrypt);
                    for (byte f : chunk) {
                        fos.write(f);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
