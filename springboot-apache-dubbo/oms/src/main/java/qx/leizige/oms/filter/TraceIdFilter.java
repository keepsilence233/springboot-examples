package qx.leizige.oms.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.UUID;

@Activate(group = CommonConstants.CONSUMER, order = 1)
public class TraceIdFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        RpcContext.getContext().setAttachment("traceId", UUID.randomUUID().toString());

        return invoker.invoke(invocation);
    }
}
