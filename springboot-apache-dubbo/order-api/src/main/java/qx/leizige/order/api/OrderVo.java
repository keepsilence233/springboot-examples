package qx.leizige.order.api;

import java.io.Serializable;

public class OrderVo implements Serializable {


    private Long id;

    private String orderNo;

    private String skuName;

    public OrderVo(Long id, String orderNo, String skuName) {
        this.id = id;
        this.orderNo = orderNo;
        this.skuName = skuName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
}
