package Des;


public class Des {

    private byte[] key;

    // length 64
    private byte[] initialPermutationTable = {
            58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17,  9, 1, 59, 51, 43, 35, 27, 19, 11, 3,
            61, 53,  5, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };

    // length 64
    private byte[] finalPermutationTable = {
            40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41,  9, 49, 17, 57, 25};

    // length 56
    private byte[] keyPermutationTable = {
            57, 49, 41, 33, 25, 17,  9,  1, 58, 50, 42, 34, 26, 18,
            10,  2, 59, 51, 43, 35, 27, 19, 11,  3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,  7, 62, 54, 46, 38, 30, 22,
            14,  6, 61, 53, 45, 37, 29, 21, 13,  5, 28, 20, 12,  4};

    // length 16
    private byte[] keyShiftTable = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    // length 12
    private byte[] compressionPermutationTable = {
            14, 17, 11, 24,  1,  5,  3, 28, 15,  6, 21, 10,
            23, 19, 12,  4, 26,  8, 16,  7, 27, 20, 13,  2,
            41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};

    // Constructor
    public Des(byte[] key) {
        this.key = key;
    }

    // public methods
    public byte[] encrypt(byte[] bits, byte[] key){

        //key = Utility.prepareKey(key);
        key = Utility.swapArrayElements(key, keyPermutationTable);

        byte[] cipher = initialPermutation(bits);
        byte[][] spltArr = Utility.splitArrayInHalf(cipher);

        byte[] leftSide = spltArr[0];
        byte[] rigthSide = spltArr[1];
        byte[] temp;

        for (int round = 0; round < 16; round++) {
            byte [] newKey = keyTransformation(key, round);

            byte[] fFunctionResult = FFunction.fun(rigthSide, newKey);

            temp = leftSide;
            leftSide = rigthSide;
            rigthSide = Utility.xorArrays(fFunctionResult, temp);
        }

        byte[] catArrays = Utility.catArrays(leftSide, rigthSide);

        return finalPermutation(catArrays);
    }

    public byte[] decrypt(String bits){
        return new byte[1];
    }

    // private methods
    private byte[] initialPermutation(byte[] bits){
        return Utility.swapArrayElements(bits, initialPermutationTable);
    }

    // round methods
    private byte[] keyTransformation(byte[] key, int round){

        int shift = keyShiftTable[round];

        byte[][] splittedKey = Utility.splitArrayInHalf(key);
        splittedKey[0] = Utility.shiftArray(splittedKey[0], shift);
        splittedKey[1] = Utility.shiftArray(splittedKey[1], shift);

        byte[] newKey = Utility.catArrays(splittedKey[0], splittedKey[1]);
        newKey = Utility.swapArrayElements(newKey, compressionPermutationTable);

        return newKey;
    }

    private byte[] finalPermutation(byte[] bits){
        return Utility.swapArrayElements(bits, finalPermutationTable);
    }

    //        return "";
//    private String sBoxSubstitution(String bits){

//    }
    //        return "";
//    private String pBoxSubstitution(String bits){

//    }
    //        return "";
//    private String xorAndSwap(String bits){

//    }

}
