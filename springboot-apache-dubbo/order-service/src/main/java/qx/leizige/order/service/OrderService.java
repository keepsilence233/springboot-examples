package qx.leizige.order.service;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import qx.leizige.order.api.OrderApi;
import qx.leizige.order.api.OrderVo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
@Service(version = "1.0.0")
public class OrderService implements OrderApi {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;

    private final static List<OrderVo> orderVoList = new ArrayList<>();

    static {
        orderVoList.add(new OrderVo(1L, "ON" + UUID.randomUUID(), "手机"));
        orderVoList.add(new OrderVo(2L, "ON" + UUID.randomUUID(), "电脑"));
        orderVoList.add(new OrderVo(3L, "ON" + UUID.randomUUID(), "空调"));
    }

    @Override
    public OrderVo getOneOrder(Long id) {
        logger.info("serviceName is : {}", serviceName);
        return orderVoList.stream().filter(v -> v.getId().equals(id)).findFirst().get();
    }

    @Override
    public List<OrderVo> getOrderList() {
        logger.info("serviceName is : {}", serviceName);
        logger.info("traceId is : {}", RpcContext.getContext().getAttachment("traceId"));
        return orderVoList;
    }
}
