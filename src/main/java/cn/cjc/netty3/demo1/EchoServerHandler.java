package cn.cjc.netty3.demo1;

import org.jboss.netty.channel.*;

/**
 * 模拟ECHO协议，将收到的数据原样返回
 *
 * @author chenjc
 * @since 2017-06-08
 */
public class EchoServerHandler extends SimpleChannelHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Channel channel = e.getChannel();
        channel.write(e.getMessage());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
