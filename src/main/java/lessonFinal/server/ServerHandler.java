import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        String[] data = s.split("\n");
        if (Integer.parseInt(data[0]) != data[1].length()) {
            channelHandlerContext.channel()
                    .writeAndFlush("3\nerr")
                    .addListener(ChannelFutureListener.CLOSE);
        }
        channelHandlerContext.channel().writeAndFlush("2\nok");
    }
}