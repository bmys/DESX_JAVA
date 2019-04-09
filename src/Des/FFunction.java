package Des;

import java.util.ArrayList;
import java.util.List;

public class FFunction {

    // length 48
    private static byte[] extensionPermutationTable ={
            32,  1,  2,  3,  4,  5,  4,  5,  6,  7,  8,  9,
             8,  9, 10, 11, 12, 12, 12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32,  1};


    public static byte[] fun(byte[] bytes, byte[] key){
        byte[][] splitArr = Utility.splitArrayInHalf(bytes);

        byte[] rSide = splitArr[1];

        // length 48 from 32 bits bytes array
        byte[] extendedArray = Utility.swapArrayElements(rSide, extensionPermutationTable);

        byte[] xorArray = Utility.xorArrays(extendedArray, key);

        byte[][] sBoxes = Utility.splitArrayInEigth(xorArray);

        List<Byte> concatenatedSBox = new ArrayList<>();
        byte[] product = new byte[32];

        for (int i = 0; i < 8; i++) {
            byte[] k = SBlock.get(i, sBoxes[i]);
            for(byte s: k){
                concatenatedSBox.add(s);
            }
        }

        for (int i = 0; i < 32; i++) {
            product[i] = concatenatedSBox.get(i);
        }


        return product;
    }
}
