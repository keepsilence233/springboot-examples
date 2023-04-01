package qx.leizige.order.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderApi {


    OrderVo getOneOrder(Long id);


    List<OrderVo> getOrderList();

}
