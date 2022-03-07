package demo;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import static demo.App.threadLocal;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/7 20:52
 */
public class LogCollector extends AppenderBase<ILoggingEvent> {

    @Override
    protected void append(final ILoggingEvent eventObject) {
        System.out.println("threadLocal.get() = " + threadLocal.get());
    }
}