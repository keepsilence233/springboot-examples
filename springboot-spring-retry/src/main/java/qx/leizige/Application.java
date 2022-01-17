package qx.leizige;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

/* @EnableRetry：加在启动类上，表示支持重试功能。 */
@EnableRetry
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Service service() {
        return new Service();
    }

    @org.springframework.stereotype.Service
    public static class Service {
        @Retryable(RemoteAccessException.class)
        public void service() {
            // ... do something
        }

        @Recover
        public void recover(RemoteAccessException e) {
            // ... panic
        }
    }
}



