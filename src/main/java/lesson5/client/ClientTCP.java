package lesson5.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientTCP {
    public static void main(String[] args) throws IOException {
        var client = SocketChannel.open(new InetSocketAddress("localhost", 9999));
        var buffer = ByteBuffer.wrap("Hello".getBytes());
        client.write(buffer);
        buffer.clear();
        client.read(buffer);
        var response = new String(buffer.array()).trim();
        System.out.println("Response: " + response);
        buffer.clear();
        buffer.put("Stop".getBytes());
        buffer.flip();
        client.write(buffer);
        client.close();
    }
}
