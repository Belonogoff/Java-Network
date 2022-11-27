package lesson5.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class ServerTCP {
    public static void main(String[] args) throws IOException {
        var selector = Selector.open();
        var serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 9999));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        var buffer = ByteBuffer.allocate(256);

        boolean run = true;

        while (run) {
            selector.select();
            var selectedKeys = selector.selectedKeys();

            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                var key = iter.next();
                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                } else if (key.isReadable()) {
                    run = process(buffer, key);
                }
                iter.remove();
            }
        }
    }

    private static boolean process(ByteBuffer buffer, SelectionKey key) throws IOException {
        var client = (SocketChannel) key.channel();
        buffer.clear();
        client.read(buffer);
        if (buffer.hasArray()) {
            String payload = new String(buffer.array(), 0, buffer.position(), StandardCharsets.UTF_8)
                    .trim();
            if ("stop".equalsIgnoreCase(payload)) {
                client.close();
                return false;
            }
            buffer.flip();
            client.write(buffer);
        }
        return true;
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        var client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }
}
