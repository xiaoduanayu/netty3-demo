package cn.cjc.netty3.demo2;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * 重要概念：a MessageEvent can be an upstream event when called for messageReceived or a downstream event when called for writeRequested.
 *
 * @author chenjc
 * @since 2017-06-08
 */
public class NettyServer {

    static final ChannelGroup allChannels = new DefaultChannelGroup("time-server");//持有全部已打开的Channel

    public static void main(String[] args) {
        ChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new TimeServerHandler(), new TimeEncoder());
            }
        });
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.bind(new InetSocketAddress(8080));

        /**
         * 在某种条件下需要关闭服务端时请打开以下代码
         Channel channel = bootstrap.bind(new InetSocketAddress(8080));
         allChannels.add(channel);
         waitForShutdownCommand();
         ChannelGroupFuture future = allChannels.close();//关闭全部连接
         future.awaitUninterruptibly();//等待连接全部关闭事件来唤醒main线程，速度比较快
         factory.releaseExternalResources();
         **/
    }

    private static void waitForShutdownCommand() {
        //阻塞，等待某个满足关闭服务的条件到来，You could wait for a message from a privileged client or the JVM shutdown hook.
    }
}