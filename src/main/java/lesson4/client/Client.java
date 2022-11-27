import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        try (DatagramSocket socket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);
            String input;
            byte[] buffer;
            while(true) {
                input = scanner.nextLine();
                buffer = input.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9090);
                socket.send(packet);
                if ("stop".equalsIgnoreCase(input)) {
                    break;
                }
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
            }
        }
    }
}
