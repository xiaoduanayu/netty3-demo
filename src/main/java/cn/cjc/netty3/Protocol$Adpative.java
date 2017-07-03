package cn.cjc.netty3;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;

public class Protocol$Adpative implements Protocol {

    @Override
    public Exporter export(Invoker invoker) {
        if (invoker == null) throw new IllegalArgumentException("Invoker argument == null");
        if (invoker.getUrl() == null) throw new IllegalArgumentException("Invoker argument getUrl() == null");
        URL url = invoker.getUrl();
        String extName = (url.getProtocol() == null ? "dubbo" : url.getProtocol());
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(Protocol) name from url(" + url.toString() + ") use keys([protocol])");
        Protocol extension = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension(extName);
        return extension.export(invoker);
    }

    @Override
    public Invoker refer(Class type, URL u) {
        if (u == null) throw new IllegalArgumentException("url == null");
        URL url = u;
        String extName = (url.getProtocol() == null ? "dubbo" : url.getProtocol());
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(Protocol) name from url(" + url.toString() + ") use keys([protocol])");
        Protocol extension = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension(extName);
        return extension.refer(type, url);
    }

    public void destroy() {
        throw new UnsupportedOperationException("method public abstract void Protocol.destroy() of interface Protocol is not adaptive method!");
    }

    public int getDefaultPort() {
        throw new UnsupportedOperationException("method public abstract int Protocol.getDefaultPort() of interface Protocol is not adaptive method!");
    }
}