package cn.cjc.netty3.demo1;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * @author chenjc
 * @since 2017-06-08
 */
public class DiscardServerHandler extends SimpleChannelHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer buf = (ChannelBuffer) e.getMessage();
        while (buf.readable()) {
            System.out.println((char) buf.readByte());
            System.out.flush();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
