package com.nuc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private String id; //商品编号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime; // 入库时间
    private String goodsName; // 商品名称
    private String goodsType; // 商品类型
    private String manufacturer; // 商品生产厂家
    private String goodsDescription; // 商品描述
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date shipTime; // 发货时间
    private String goodsPrice; // 单价
    private String goodsNum; // 数量

    private List<Evaluate> evaluate; // 商品评价

}
