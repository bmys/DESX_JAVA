package Des;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Des {

    private byte[] key;

    // length 64
    private static byte[] initialPermutationTable = {
            58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17,  9, 1, 59, 51, 43, 35, 27, 19, 11, 3,
            61, 53,  45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };

    // length 64
    private byte[] finalPermutationTable = {
            40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41,  9, 49, 17, 57, 25};

    // length 56
    private static byte[] keyPermutationTable = {
            57, 49, 41, 33, 25, 17,  9,  1, 58, 50, 42, 34, 26, 18,
            10,  2, 59, 51, 43, 35, 27, 19, 11,  3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,  7, 62, 54, 46, 38, 30, 22,
            14,  6, 61, 53, 45, 37, 29, 21, 13,  5, 28, 20, 12,  4};

    // length 16
    private static byte[] keyShiftTable = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    // length 12
    private static byte[] compressionPermutationTable = {
            14, 17, 11, 24,  1,  5,  3, 28, 15,  6, 21, 10,
            23, 19, 12,  4, 26,  8, 16,  7, 27, 20, 13,  2,
            41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};

    // Constructor

    // public methods

    private List<byte[]> subKeys = new LinkedList<>();

    private List<byte[]> generateSubKeys(byte[] key){
        List<byte[]> subKeys = new LinkedList<>();

        byte[] newKey = key;
        for (int round = 0; round < 16; round++) {
            int shift;
            shift = keyShiftTable[round];

            byte[][] splittedKey = Utility.splitArrayInHalf(newKey);
            splittedKey[0] = Utility.shiftArray(splittedKey[0], shift);
            splittedKey[1] = Utility.shiftArray(splittedKey[1], shift);

            newKey = Utility.catArrays(splittedKey[0], splittedKey[1]);
            subKeys.add(KeyCompression(newKey));
        }
        return subKeys;
    }

    public byte[] encrypt(byte[] bits, byte[] key, boolean decrypt){

        byte[] cipher = Utility.swapArrayElements(bits, initialPermutationTable);

        byte[][] spltArr = Utility.splitArrayInHalf(cipher);

        byte[] leftSide = spltArr[0];
        byte[] rigthSide = spltArr[1];
        byte[] temp;

        key = Utility.swapArrayElements(key, keyPermutationTable);

        this.subKeys = generateSubKeys(key);
        if(decrypt){
            Collections.reverse(this.subKeys);
        }

        for (int round = 0; round < 16; round++) {
            byte[] fFunctionResult = FFunction.fun(rigthSide, subKeys.get(round));

            temp = leftSide;
            leftSide = rigthSide;
            rigthSide = Utility.xorArrays(fFunctionResult, temp);
            }

        byte[] catArrays = Utility.catArrays(rigthSide, leftSide);

        return finalPermutation(catArrays);
    }

    public static byte[] initialPermutation(byte[] bits){
        return Utility.swapArrayElements(bits, initialPermutationTable);
    }

    public static byte[] KeyPermutation(byte[] key){
        return Utility.swapArrayElements(key, keyPermutationTable);
    }

    public static byte[] KeyCompression(byte[] key){
        return Utility.swapArrayElements(key, compressionPermutationTable);
    }

    private byte[] finalPermutation(byte[] bits){
        return Utility.swapArrayElements(bits, finalPermutationTable);
    }
}
