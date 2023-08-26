package com.nuc.controller;

import com.nuc.pojo.Evaluate;
import com.nuc.pojo.Order;
import com.nuc.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 添加商品至 MongoDB
     *
     * @param order
     * @return
     */
    @PostMapping("add")
    public String addOrder(Order order) {
        orderService.addOrder(order);
        return "订单添加成功";
    }

    /**
     * 更新商品信息
     * 添加评论信息
     *
     * @param evaluate
     * @return
     */
    @PostMapping("update")
    public String updateOrderAndAddLogistics(Evaluate evaluate) {
        orderService.updateEvalute(evaluate);
        return "评价添加成功";
    }

    /**
     * 通过商品编号查询
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Order selectOrderById(@PathVariable String id) {
        return orderService.selectOrderById(id);
    }

    /**
     * 查询所有订单
     *
     * @return
     */
    @GetMapping("list")
    public Map<String, Object> selectOrderList() {
        return orderService.selectOrderList();
    }

    /**
     * 根据订单编号删除订单记录
     *
     * @param id
     * @return
     */
    @PostMapping("delete")
    public String deleteById(String id) {
        orderService.deleteOrderById(id);
        return "删除成功";
    }

}
