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

package com.lazycece.tradecore.infra.acl.respository;

import com.lazycece.rapidf.domain.anotation.DomainRepository;
import com.lazycece.rapidf.domain.model.Pagination;
import com.lazycece.rapidf.utils.UUIDUtils;
import com.lazycece.tradecore.domain.order.model.OrderInfo;
import com.lazycece.tradecore.domain.order.model.OrderQueryCondition;
import com.lazycece.tradecore.domain.order.repository.OrderInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author lazycece
 * @date 2023/2/12
 */
@DomainRepository
public class OrderInfoRepositoryImpl implements OrderInfoRepository {

    /**
     * 集合资源库
     */
    private final List<OrderInfo> orderInfoList = new ArrayList<>();

    @Override
    public String insert(OrderInfo orderInfo) {
        String orderId = UUIDUtils.uuid();
        orderInfo.setOrderId(orderId);
        orderInfoList.add(orderInfo);
        return orderId;
    }

    @Override
    public OrderInfo queryByOrderId(String userId, String orderId) {
        Optional<OrderInfo> optional = orderInfoList.stream()
                .filter(orderInfo ->
                        orderInfo.getOrderId().equals(orderId) && orderInfo.getUserId().equals(userId))
                .findFirst();
        return optional.orElse(null);
    }

    @Override
    public List<OrderInfo> queryByCondition(OrderQueryCondition queryCondition, Pagination pagination) {
        return null;
    }
}
