package qx.leizige.oms.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.util.StopWatch;


@Activate(group = CommonConstants.CONSUMER, order = 2)
public class LogFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        StopWatch stopWatch = new StopWatch();
        //拿到调用的服务url
        System.out.println("开始调用:" + invoker.getUrl());
        stopWatch.start();
        Result result = invoker.invoke(invocation);
        stopWatch.stop();
        // 输出调用结果
        System.out.println("调用结果:" + result.getValue());
        //输出调用耗时
        System.out.println("调用耗时(秒):" + stopWatch.getTotalTimeSeconds());
        return result;
    }
}
