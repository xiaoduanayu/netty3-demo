package cn.cjc.netty3.demo2;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * 重要概念：a MessageEvent can be an upstream event when called for messageReceived or a downstream event when called for writeRequested.
 *
 * @author chenjc
 * @since 2017-06-08
 */
public class NettyClient {

    public static void main(String[] args) {
        ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        ClientBootstrap bootstrap = new ClientBootstrap(factory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new TimeDecoder(), new TimeClientHandler());
            }
        });
        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 8080));
        future.awaitUninterruptibly();//等待连接完成事件来唤醒main线程，此处很快被唤醒
        if (!future.isSuccess()) {//连接失败
            future.getCause().printStackTrace();
        }
        future.getChannel().getCloseFuture().awaitUninterruptibly();//等待连接关闭事件来唤醒main线程。如果连接失败，会自动关闭连接，产生连接关闭事件唤醒此处
        factory.releaseExternalResources();
    }
}