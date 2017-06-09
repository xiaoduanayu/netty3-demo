package cn.cjc.netty3.demo2;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * @author chenjc
 * @since 2017-06-09
 */
public class TimeServerHandler extends SimpleChannelHandler {
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        Channel channel = e.getChannel();
        ChannelBuffer buffer = ChannelBuffers.buffer(4);
        buffer.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        ChannelFuture future = channel.write(buffer);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                future.getChannel().close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
