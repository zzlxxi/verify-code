package ink.icopy.verifycode.event;

import okhttp3.Call;
import okhttp3.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.List;

/**
 * @author lizhengguang
 * @date 2019-8-16 11:44:22
 */
public class OkHttpListener extends EventListener {
    private long callStartNanos;
    private Logger log = LoggerFactory.getLogger(OkHttpListener.class);

    @Override
    public void callStart(Call call) {
        printEvent("callStart");
    }

    @Override
    public void dnsStart(Call call, String domainName) {
        super.dnsStart(call, domainName);
    }

    @Override
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        printEvent("dnsEnd");
    }


    @Override
    public void callEnd(Call call) {
        printEvent("callEnd");
    }

    private void printEvent(String name) {
        long nowNanos = System.nanoTime();
        if ("callStart".equals(name)) {
            callStartNanos = nowNanos;
        }
        long elapsedNanos = nowNanos - callStartNanos;
        log.info("%.3f %s%n", elapsedNanos / 1000000000d, name);
    }
}
