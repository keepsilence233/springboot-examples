package qx.leizige.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;



@Service
@EnableRetry
public class RetryService {

    private  static final Logger LOGGER = LoggerFactory.getLogger(RetryService.class);

    /*@Retryable：加在方法上，就会给这个方法赋能，让它有用重试的功能。*/
    @Retryable
    public void callChannel() throws Exception {
        queryStore();
    }


    /* @Recover：重试完成后还是不成功的情况下，会执行被这个注解修饰的方法。 */
    @Recover
    public void channelNotResp() {
        LOGGER.info("没有获取到渠道的返回信息,发出预警!");
    }


    void queryStore() throws Exception{
        throw new TimeoutException("调用渠道中心获取店铺超时!");
    }


}
