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

package com.lazycece.tradecore.domain.order.model;

import com.lazycece.rapidf.domain.anotation.DomainAggregate;
import com.lazycece.rapidf.domain.model.Aggregate;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lazycece
 * @date 2023/2/11
 */
@Getter
@Setter
@DomainAggregate
public class OrderInfo extends Aggregate<String> {

    private String orderId;
    private String userId;
    private String addressId;
    private BigDecimal amount;
    private OrderStatus orderStatus;
    private List<OrderDetail> orderDetailList;

    @Override
    public String getId() {
        return this.orderId;
    }
}
