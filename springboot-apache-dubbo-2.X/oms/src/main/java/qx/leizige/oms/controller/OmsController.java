package qx.leizige.oms.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qx.leizige.order.api.OrderApi;
import qx.leizige.order.api.OrderVo;

import java.util.List;

@RestController
@RequestMapping(value = "/oms")
public class OmsController {

    @Reference(version = "1.0.0")
    private OrderApi orderApi;

    @GetMapping(value = "/getOneOrder/{id}")
    public OrderVo getOneOrder(@PathVariable("id") Long id){
        return orderApi.getOneOrder(id);
    }

    @GetMapping(value = "/getOrderList")
    public List<OrderVo> getOrderList() {
        return orderApi.getOrderList();
    }

}
