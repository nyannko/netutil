import java.util.Arrays;
import java.util.Scanner;

public class ComputeNetwork {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ip = in.next();
        int netmask = in.nextInt();
        //check input
        String[] str = ip.split("\\.");
        StringBuffer r2 = new StringBuffer();
        //change 10-radix - 8 bit 2-radix stringbuffer
        for(int i = 0; i < str.length; i++) {
            int part = Integer.parseInt(str[i]);
            StringBuffer binPart = new StringBuffer();
            binPart.append(Integer.toBinaryString(part));
            if (binPart.length() < 8) {
                for (int j = 0; j < 8 - Integer.toBinaryString(part).length(); j++) {
                    binPart.insert(0, "0");
                }
            }
            r2.append(binPart);
        }
        filledWithSequence(r2, '0', netmask);
        //convert binary string to decimal
        StringBuffer networkAddress = new StringBuffer();
        StringBuffer broadcastAddress = new StringBuffer();
        constructResult(r2, networkAddress);
        filledWithSequence(r2, '1', netmask);
        constructResult(r2, broadcastAddress);

        System.out.println("Network address: " + networkAddress + "/" + netmask);
        System.out.println("Broadcast address: " + broadcastAddress);
        int lastIdx = broadcastAddress.lastIndexOf(".");
        int lastIdx1 = networkAddress.lastIndexOf(".");
        int num1 = Integer.valueOf(networkAddress.substring((lastIdx1 + 1))) + 1;
        int num = Integer.valueOf(broadcastAddress.substring(lastIdx + 1)) - 1;
        System.out.println("Minimum host: " + networkAddress.replace(lastIdx1 + 1, networkAddress.length(), String.valueOf(num1)));
        System.out.println("Maximum host: " + broadcastAddress.replace( lastIdx + 1, broadcastAddress.length(), String.valueOf(num)));
        System.out.printf("Host number: %d", (int) Math.pow(2, 32 - netmask) - 2);
    }

    /**
     * Construct a sequence with 0 or 1.
     * @param r2 StringBuffer
     * @param ch 0 or 1
     * @param netmask netmask
     */
    public static void filledWithSequence(StringBuffer r2, char ch, int netmask) {
        char[] charZero = new char[32 - netmask];
        Arrays.fill(charZero, ch);
        r2.replace(netmask, 32, new String(charZero));
    }

    /**
     * Construct address result from filled StringBuffer.
     * @param r2 StringBuffer
     * @param out result
     */
    public static void constructResult(StringBuffer r2, StringBuffer out) {
        String[] arr = new String[4];
        int j = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            arr[i] = r2.substring(j, j+8);
            j += 8;
        }
        arr[3] = r2.substring(24);
        for(int i = 0; i < arr.length; i++) {
            out.append(Integer.valueOf(arr[i],2).toString());
            if(i < arr.length - 1) {
                out.append(".");
            }
        }
    }
}
