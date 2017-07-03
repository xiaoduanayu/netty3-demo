package cn.cjc.netty3;

/**
 * @author chenjc
 * @since 2017-07-03
 */
public class MainTest {

    public Object invokeMethod(Object o, String n, Class[] p, Object[] v) throws java.lang.reflect.InvocationTargetException {
        cn.cjc.dubbo.provider.impl.HelloServiceImpl w;
        try {
            w = ((cn.cjc.dubbo.provider.impl.HelloServiceImpl) $1);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        try {
            if ("sayHi".equals($2) && $3.length == 1) {
                return ($w) w.sayHi((String) $4[0]);
            }
        } catch (Throwable e) {
            throw new java.lang.reflect.InvocationTargetException(e);
        }
        throw new com.alibaba.dubbo.common.bytecode.NoSuchMethodException("Not found method \"" + $2 + "\" in class cn.cjc.dubbo.provider.impl.HelloServiceImpl.");
    }
}
