import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GetARPAdderss {
    public static void main(String[] args) throws SocketException {
        Scanner in = new Scanner(System.in);
        InetAddress host = null;
        try {
            host = InetAddress.getByName(in.nextLine());
        } catch (UnknownHostException e) {
            System.out.println("host doesn't exist!");
        }
        NetworkInterface ni = NetworkInterface.getByInetAddress(host);
        if(ni == null) {
            System.out.println("network interface doesn't exist!");
        } else {
            byte[] mac = ni.getHardwareAddress();
            for(int i = 0; i < mac.length; i++) {
                System.out.printf("%2X%s", mac[i], (i < (mac.length - 1)) ? ":" : "");
            }
        }
    }
}