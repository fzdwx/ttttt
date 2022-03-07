package demo;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.alibaba.ttl.threadpool.agent.TtlAgent;

import static demo.App.threadLocal;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/7 20:52
 */
public class LogCollector extends AppenderBase<ILoggingEvent> {

    @Override
    protected void append(final ILoggingEvent eventObject) {
        //  -javaagent:C:\Users\98065\.m2\repository\com\alibaba\transmittable-thread-local\2.12.4\transmittable-thread-local-2.12.4.jar
        System.out.println("threadLocal.get() = " + threadLocal.get());
    }
}