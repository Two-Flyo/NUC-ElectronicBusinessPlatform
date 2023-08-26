package com.nuc.service;

import cn.hutool.core.util.IdUtil;
import com.nuc.pojo.Evaluate;
import com.nuc.pojo.Order;
import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 商品信息管理
     *
     * @param order
     */
    public void addOrder(Order order) {
        // 订单编号根据雪花算法生成
        order.setId(IdUtil.getSnowflake(1, 1).nextIdStr());
        // 设置下单时间
        order.setOrderTime(new Date());
        // 添加订单至 MongoDB
        mongoTemplate.insert(order, "order");
    }

    /**
     * 商品评论
     * @param evaluate
     */
    public void updateEvalute(Evaluate evaluate) {

        // 设置操作时间
        evaluate.setEvaluateTime(new Date());
        // 初始化 Query 对象，根据订单编号查询
        Query query = new Query(Criteria.where("_id").is(evaluate.getGoodsId()));
        // 初始化 Update 对象
        Update update = new Update();
        // 追加物流信息
        update.push("evaluate", evaluate);
        // 更新订单信息
        mongoTemplate.upsert(query, update, Order.class, "order");
    }

    /**
     * 通过商品编号查询
     *
     * @param id
     * @return
     */
    public Order selectOrderById(String id) {
        // 初始化 Query 对象，根据订单编号查询
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Order.class, "order");
    }

    /**
     * 查询所有
     *
     * @return
     */
    public Map<String, Object> selectOrderList() {
        List<Order> list = mongoTemplate.findAll(Order.class, "order");
        Map<String, Object> result = new HashMap<>();
        if (list == null || list.isEmpty()) {
            result.put("code", "400");
            result.put("message", "没有相关订单信息");
        } else {
            result.put("code", "0");
            result.put("count", list.size());
            result.put("data", list);
        }
        return result;
    }

    /**
     * 根据商品编号删除
     *
     * @param id
     * @return
     */
    public boolean deleteOrderById(String id) {
        // 初始化 Query 对象，根据订单编号查询
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, Order.class, "order");
        return result.getDeletedCount() > 0 ? true : false;
    }

}
