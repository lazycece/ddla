#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 *    Copyright 2023 lazycece<lazycece@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ${package}.domain.order.service.impl;

import com.lazycece.rapidf.domain.anotation.DomainService;
import com.lazycece.rapidf.restful.Assert;
import com.lazycece.rapidf.restful.response.RespStatus;
import com.lazycece.rapidf.utils.DefaultUtils;
import ${package}.domain.order.event.OrderDomainEventPublisher;
import ${package}.domain.order.event.OrderEventType;
import ${package}.domain.order.model.OrderDetail;
import ${package}.domain.order.model.OrderEvent;
import ${package}.domain.order.model.OrderInfo;
import ${package}.domain.order.repository.OrderDetailRepository;
import ${package}.domain.order.repository.OrderInfoRepository;
import ${package}.domain.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author lazycece
 * @date 2023/2/12
 */
@DomainService
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private OrderDomainEventPublisher eventPublisher;

    @Override
    public String createOrder(OrderInfo orderInfo) {
        Assert.notNull(orderInfo, RespStatus.PARAM_ERROR, "订单信息不能为null");
        List<OrderDetail> orderDetailList = orderInfo.getOrderDetailList();
        Assert.notEmpty(orderDetailList, RespStatus.PARAM_ERROR, "订单明细不能为空");

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                String orderId = orderInfoRepository.insert(orderInfo);
                DefaultUtils.defaultList(orderDetailList)
                        .forEach(orderDetail -> orderDetail.setOrderId(orderId));
                orderDetailRepository.batchInsert(orderDetailList);
                eventPublisher.publish(OrderEvent.build(OrderEventType.ORDER_CREATED, orderInfo));
            }
        });
        return orderInfo.getOrderId();
    }

    @Override
    public void cancelOrder(String userId, String orderId) {

    }
}
