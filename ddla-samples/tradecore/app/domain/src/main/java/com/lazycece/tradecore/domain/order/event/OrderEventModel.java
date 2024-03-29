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

package com.lazycece.tradecore.domain.order.event;

import com.lazycece.rapidf.domain.anotation.DomainFactory;
import com.lazycece.rapidf.domain.anotation.ValueObject;
import com.lazycece.tradecore.domain.order.model.OrderEventAction;
import com.lazycece.tradecore.domain.order.model.OrderInfo;
import com.lazycece.tradecore.domain.order.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author lazycece
 * @date 2023/2/12
 */
@ValueObject
@Getter
@Setter
public class OrderEventModel {

    private OrderEventAction action;
    private String orderId;
    private String userId;
    private String addressId;
    private BigDecimal amount;
    private OrderStatus orderStatus;

    @DomainFactory
    public static OrderEventModel build(OrderEventAction action, OrderInfo orderInfo) {
        OrderEventModel event = new OrderEventModel();
        event.setAction(action);
        event.setUserId(orderInfo.getUserId());
        event.setOrderId(orderInfo.getOrderId());
        event.setAddressId(orderInfo.getAddressId());
        event.setAmount(orderInfo.getAmount());
        event.setOrderStatus(orderInfo.getOrderStatus());
        return event;
    }
}
