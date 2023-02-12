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

package com.lazycece.tradecore.application.order;

import com.lazycece.rapidf.domain.anotation.ApplicationService;
import com.lazycece.rapidf.restful.Assert;
import com.lazycece.rapidf.restful.response.RespData;
import com.lazycece.rapidf.restful.response.RespStatus;
import com.lazycece.tradecore.application.order.assembler.OrderAssembler;
import com.lazycece.tradecore.application.order.validator.OrderCreateRequestValidator;
import com.lazycece.tradecore.domain.goods.model.Goods;
import com.lazycece.tradecore.domain.goods.repository.GoodsRepository;
import com.lazycece.tradecore.domain.order.model.OrderInfo;
import com.lazycece.tradecore.domain.order.service.OrderService;
import com.lazycece.tradecore.facade.order.api.OrderCommandFacade;
import com.lazycece.tradecore.facade.order.request.OrderCancelRequest;
import com.lazycece.tradecore.facade.order.request.OrderCreateRequest;
import com.lazycece.tradecore.facade.order.result.OrderCreateResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lazycece
 * @date 2023/2/11
 */
@ApplicationService
public class OrderCommandFacadeImpl implements OrderCommandFacade {

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public RespData<OrderCreateResult> createOrder(OrderCreateRequest request) {
        OrderCreateRequestValidator.validate(request);

        List<String> goodsIdList = request.getGoodBuyInfoList()
                .stream().map(OrderCreateRequest.GoodBuyInfo::getGoodsId)
                .collect(Collectors.toList());
        List<Goods> goodsList = goodsRepository.queryByGoodsIdList(goodsIdList);
        OrderInfo orderInfo = OrderAssembler.assembleOrderInfo(request, goodsList);
        String orderId = orderService.createOrder(orderInfo);

        OrderCreateResult result = new OrderCreateResult();
        result.setOrderId(orderId);
        return RespData.success(result);
    }

    @Override
    public RespData<Void> cancelOrder(OrderCancelRequest request) {
        Assert.notNull(request, RespStatus.PARAM_ERROR, "取消订单请求不能为null");
        Assert.notBlank(request.getUserId(), RespStatus.PARAM_ERROR, "userId不能为空");
        Assert.notBlank(request.getOrderId(), RespStatus.PARAM_ERROR, "orderId不能为空");
        orderService.cancelOrder(request.getUserId(), request.getOrderId());
        return RespData.success(null);
    }
}
