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

package com.lazycece.tradecore.application.order.converter;

import com.lazycece.rapidf.utils.DefaultUtils;
import com.lazycece.tradecore.domain.order.model.OrderDetail;
import com.lazycece.tradecore.domain.order.model.OrderInfo;
import com.lazycece.tradecore.domain.order.model.OrderStatus;
import com.lazycece.tradecore.facade.order.dto.OrderDetailDTO;
import com.lazycece.tradecore.facade.order.dto.OrderInfoDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lazycece
 * @date 2023/2/12
 */
public class OrderConverter {

    public static OrderInfoDTO toOrderInfoDTO(OrderInfo model) {
        OrderInfoDTO dto = new OrderInfoDTO();
        dto.setOrderId(model.getOrderId());
        dto.setUserId(model.getUserId());
        dto.setAddressId(model.getAddressId());
        dto.setAmount(model.getAmount());
        dto.setOrderStatus(DefaultUtils.defaultValueIfNullObj(model.getOrderStatus(),
                OrderStatus::getCode, null));
        dto.setOrderDetailList(toOrderDetailDTOList(model.getOrderDetailList()));
        return dto;
    }

    private static List<OrderDetailDTO> toOrderDetailDTOList(List<OrderDetail> modelList) {
        return DefaultUtils.defaultList(modelList)
                .stream().map(model -> {
                    OrderDetailDTO dto = new OrderDetailDTO();
                    dto.setOrderDetailId(model.getOrderDetailId());
                    dto.setOrderId(model.getOrderId());
                    dto.setUserId(model.getUserId());
                    dto.setGoodsId(model.getGoodsId());
                    dto.setGoodCount(model.getGoodCount());
                    dto.setGoodsPrice(model.getGoodsPrice());
                    dto.setAmount(model.getAmount());
                    return dto;
                }).collect(Collectors.toList());
    }
}
