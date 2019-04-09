package Des;

import java.util.Arrays;

public class Utility {
    public static byte[] swapArrayElements(byte[] arr, byte[] indexesArr){
        byte[] swappedArr = new byte[indexesArr.length];

        for (int i = 0; i < indexesArr.length; i++) {
            byte index = indexesArr[i];
            swappedArr[i] = arr[index];
        }

        return swappedArr;
    }

    public static byte[] xorArrays(byte[] arr, byte[] key){
        byte[] xorArray = new byte[arr.length];

        for (int i = 0; i < arr.length; i++)
            xorArray[i] = (byte)(arr[i] ^ key[i]);

        return xorArray;
    }

    public static byte[] prepareKey(byte[] key){
        byte[] newKey = new byte[56];
        int j = 0;

        for (int i = 0; i < 64; i++) {
            if(i % 8 == 0) continue;
            newKey[j] = key[i];
            j++;
        }
        return newKey;
    }

    public static byte[][] splitArrayInHalf(byte[] arr){
        int halfIndex = arr.length / 2;

        byte[][] returnArr = new byte[2][halfIndex];

        byte[] leftArray = new byte[halfIndex];
        byte[] rigthArray = new byte[halfIndex];

        for (int i = 0; i < halfIndex; i++) {
            returnArr[0][i] = arr[i];
            returnArr[1][i+halfIndex] = arr[i+halfIndex];
        }

        return returnArr;
    }

    public static byte[][] splitArrayInEigth(byte[] arr){
        byte[][] newArr = new byte[8][6];

        for (int i = 0; i < 8; i++) {
            newArr[i] = Arrays.copyOfRange(arr, i*8, i*8+6);
        }
        return newArr;
    }

//    public static byte[][] splitArrayInEigth(byte[] arr){
//       byte[][] newArr = new byte[8][6];
//
//        for (int i = 0; i < 8; i++) {
//            byte[] iterArr = new byte[6];
//
//            for (int j = 0; j < 6; j++) {
//                int idx = i*5+j;
//                iterArr[j] = arr[idx];
//            }
//
//            newArr[i] = iterArr;
//        }
//       return newArr;
//    }

    public static byte[] shiftArray(byte[] arr, int num){
        return arr;
    }

    public static byte[] catArrays(byte[] arr1, byte[] arr2){

        int totalSize = arr1.length + arr2.length;
        byte[] newArr = new byte[totalSize];

        System.arraycopy(arr1, 0, newArr, 0, arr1.length);

        System.arraycopy(arr2, 0, newArr, arr1.length, totalSize - arr1.length);

        return newArr;
    }

    public static int toInt(byte[] arr){
        int sum = 0;
        int counter = 1;
        for (int i = arr.length-1; i != -1  ; i--) {
                sum += arr[i] * counter;
                counter *= 2;
        }
        return sum;
    }

    public static byte[] toByteArray(int k){
        byte[] arr = new byte[4];

        int counter = 8;

        for (int i = 0; i < 4; i++) {
            if(k >= counter) {
                arr[i] = 1;
                k -= counter;
            }
            counter /= 2;
        }

        return arr;
    }
}