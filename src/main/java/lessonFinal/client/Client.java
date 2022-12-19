import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(3);
        Bootstrap bootstrap = new Bootstrap()
                .group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientChannelInitializer());

        List<String> list = Arrays.asList("5\nhello", "4\ncool", "6\nhaha");

        try {
            for (String line : list) {
                System.out.println(line);
                Channel channel = bootstrap.connect("localhost", 8080).sync().channel();
                channel.writeAndFlush(line);
                channel.closeFuture().sync();
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}