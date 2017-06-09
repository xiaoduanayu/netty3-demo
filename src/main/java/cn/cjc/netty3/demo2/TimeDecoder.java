package cn.cjc.netty3.demo2;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * 解决粘包/拆包
 *
 * @author chenjc
 * @since 2017-06-09
 */
public class TimeDecoder extends FrameDecoder {
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
        if (buffer.readableBytes() < 4)
            return null;
        return new UnixTime(buffer.readInt());
    }
}
