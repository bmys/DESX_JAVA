package Des;

public class DesX extends Des {
    @Override
    public byte[] encrypt(byte[] bits, byte[] key, boolean decrypt) {

        byte[] firstXorKey = new byte[64];
        System.arraycopy(key, 0, firstXorKey, 0, 64);

        byte[] realKey = new byte[56];
        System.arraycopy(key, firstXorKey.length, realKey, 0, 56);

        byte[] lastXorKey = new byte[64];
        System.arraycopy(key, firstXorKey.length + realKey.length, lastXorKey, 0, 64);

//        byte[] xored = Utility.xorArrays(bits, firstXorKey);
        byte[] cipher = super.encrypt(bits, realKey, decrypt);
//        xored = Utility.xorArrays(cipher, lastXorKey);

        return cipher;
    }
}
