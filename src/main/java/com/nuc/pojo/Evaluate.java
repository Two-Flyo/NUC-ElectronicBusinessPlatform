package com.nuc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品评论
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluate implements Serializable {

    private String goodsId; // 商品编号
    private String level; // 商品描述等级
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date evaluateTime; // 评价时间
    private String name; // 评价用户
    private String evaluateInfo; // 评价信息

}
