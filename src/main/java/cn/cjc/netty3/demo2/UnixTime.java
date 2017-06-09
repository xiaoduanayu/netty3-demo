package cn.cjc.netty3.demo2;

import java.util.Date;

/**
 * 传输对象用POJO，以此来解耦ChannelBuffer
 *
 * @author chenjc
 * @since 2017-06-09
 */
public class UnixTime {
    private final int value;

    public UnixTime(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new Date(value * 1000).toString();
    }
}
