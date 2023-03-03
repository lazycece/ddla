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

package ${package}.application.order.assembler;

import com.lazycece.rapidf.utils.DefaultUtils;
import ${package}.domain.goods.model.Goods;
import ${package}.domain.order.model.OrderDetail;
import ${package}.domain.order.model.OrderInfo;
import ${package}.domain.order.model.OrderStatus;
import ${package}.facade.order.request.OrderCreateRequest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lazycece
 * @date 2023/2/12
 */
public class OrderAssembler {

    public static OrderInfo assembleOrderInfo(OrderCreateRequest request, List<Goods> goodsList) {
        OrderInfo orderInfo = new OrderInfo();
        List<OrderDetail> detailList = assembleOrderDetails(request, goodsList);
        orderInfo.setOrderDetailList(detailList);
        orderInfo.setUserId(request.getUserId());
        orderInfo.setAddressId(request.getAddressId());
        BigDecimal amount = DefaultUtils.defaultList(detailList)
                .stream().map(OrderDetail::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        orderInfo.setAmount(amount);
        orderInfo.setOrderStatus(OrderStatus.WAIT_PAY);
        orderInfo.setCreator(request.getUserId());
        orderInfo.setUpdater(request.getUserId());
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderInfo.setDeleted(false);
        return orderInfo;
    }

    private static List<OrderDetail> assembleOrderDetails(OrderCreateRequest request, List<Goods> goodsList) {
        Map<String, Goods> goodsMap = DefaultUtils.defaultList(goodsList)
                .stream().collect(Collectors.toMap(Goods::getGoodsId, o -> o));
        return DefaultUtils.defaultList(request.getGoodBuyInfoList())
                .stream().map(goodBuyInfo -> {
                    Goods goods = goodsMap.get(goodBuyInfo.getGoodsId());
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setUserId(request.getUserId());
                    orderDetail.setGoodsId(goodBuyInfo.getGoodsId());
                    orderDetail.setGoodCount(goodBuyInfo.getCount());
                    orderDetail.setGoodsPrice(goods.getPrice());
                    orderDetail.setAmount(goods.getPrice().multiply(BigDecimal.valueOf(goodBuyInfo.getCount())));
                    orderDetail.setCreator(request.getUserId());
                    orderDetail.setUpdater(request.getUserId());
                    orderDetail.setCreateTime(new Date());
                    orderDetail.setUpdateTime(new Date());
                    orderDetail.setDeleted(false);
                    return orderDetail;
                }).collect(Collectors.toList());
    }


}
