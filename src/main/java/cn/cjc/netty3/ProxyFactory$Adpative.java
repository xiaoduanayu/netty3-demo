package cn.cjc.netty3;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.ProxyFactory;

public class ProxyFactory$Adpative implements ProxyFactory {
    @Override
    public Invoker getInvoker(Object proxy, Class type, URL u) {
        if (u == null) throw new IllegalArgumentException("url == null");
        URL url = u;
        String extName = url.getParameter("proxy", "javassist");
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");
        ProxyFactory extension = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension(extName);
        return extension.getInvoker(proxy, type, url);
    }

    @Override
    public Object getProxy(Invoker invoker) {
        if (invoker == null) throw new IllegalArgumentException("Invoker argument == null");
        if (invoker.getUrl() == null) throw new IllegalArgumentException("Invoker argument getUrl() == null");
        URL url = invoker.getUrl();
        String extName = url.getParameter("proxy", "javassist");
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");
        ProxyFactory extension = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension(extName);
        return extension.getProxy(invoker);
    }
}