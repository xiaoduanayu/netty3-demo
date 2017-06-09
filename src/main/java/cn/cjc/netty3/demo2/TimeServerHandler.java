package cn.cjc.netty3.demo2;

import org.jboss.netty.channel.*;

/**
 * 模拟TIME协议，连接一旦建立，立马给客户端发送当前时间
 *
 * @author chenjc
 * @since 2017-06-09
 */
public class TimeServerHandler extends SimpleChannelHandler {
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        Channel channel = e.getChannel();
        UnixTime unixTime = new UnixTime((int) (System.currentTimeMillis() / 1000));
        ChannelFuture future = channel.write(unixTime);//可以直接写入ChannelBuffer实例，但是写入POJO是更好解耦，在编码层再将POJO转换成ChannelBuffer即可
        //等到操作完成再关闭连接，如果直接跟上channel.close();有可能会出现消息还没完全发送出去连接就被关闭的情况
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                future.getChannel().close();
                System.out.println("关闭连接");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        NettyServer.allChannels.add(e.getChannel());//每打开一个连接，就保存下来，以备后用
    }
}
