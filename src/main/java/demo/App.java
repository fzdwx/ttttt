package demo;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/7 20:12
 */
@WebFilter(urlPatterns = "/*")
@SpringBootApplication
@RestController
@Slf4j
public class App implements Filter {

    public static TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        try {
            threadLocal.set(cn.hutool.core.util.IdUtil.getSnowflakeNextIdStr());
            chain.doFilter(request, response);
        } finally {
            threadLocal.remove();
        }
    }

    @GetMapping
    public void test() {
        System.out.println(threadLocal.get());
        new Thread(() -> {
            for (; ; ) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("ttttttttttttt");
            }
        }).start();
    }
}