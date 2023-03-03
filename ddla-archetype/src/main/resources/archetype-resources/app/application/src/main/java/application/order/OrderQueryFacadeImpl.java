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

package ${package}.application.order;

import com.lazycece.rapidf.domain.anotation.ApplicationService;
import com.lazycece.rapidf.domain.model.Pagination;
import com.lazycece.rapidf.restful.Assert;
import com.lazycece.rapidf.restful.PageData;
import com.lazycece.rapidf.restful.response.RespData;
import com.lazycece.rapidf.restful.response.RespStatus;
import com.lazycece.rapidf.utils.DefaultUtils;
import com.lazycece.rapidf.utils.EnumUtils;
import ${package}.application.order.converter.OrderConverter;
import ${package}.domain.order.model.OrderInfo;
import ${package}.domain.order.model.OrderQueryCondition;
import ${package}.domain.order.model.OrderStatus;
import ${package}.domain.order.repository.OrderInfoRepository;
import ${package}.facade.order.api.OrderQueryFacade;
import ${package}.facade.order.dto.OrderInfoDTO;
import ${package}.facade.order.request.OrderListQueryRequest;
import ${package}.facade.order.request.OrderQueryRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lazycece
 * @date 2023/2/11
 */
@ApplicationService
public class OrderQueryFacadeImpl implements OrderQueryFacade {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Override
    public RespData<OrderInfoDTO> queryOrder(OrderQueryRequest request) {
        Assert.notNull(request, RespStatus.PARAM_ERROR, "订单查询请求不能为null");
        Assert.notBlank(request.getUserId(), RespStatus.PARAM_ERROR, "userId不能为空");
        Assert.notBlank(request.getOrderId(), RespStatus.PARAM_ERROR, "orderId不能为空");

        OrderInfo orderInfo = orderInfoRepository.queryByOrderId(request.getUserId(), request.getOrderId());
        Assert.notNull(orderInfo, RespStatus.DATA_NOT_EXIST, "订单信息不存在");

        return RespData.success(OrderConverter.toOrderInfoDTO(orderInfo));
    }

    @Override
    public RespData<PageData<OrderInfoDTO>> queryOrderList(OrderListQueryRequest request) {
        Assert.notNull(request, RespStatus.PARAM_ERROR, "订单列表查询请求不能为null");
        Assert.notBlank(request.getUserId(), RespStatus.PARAM_ERROR, "userId不能为空");

        OrderQueryCondition queryCondition = new OrderQueryCondition();
        queryCondition.setUserId(request.getUserId());

        if (StringUtils.isNotBlank(request.getOrderStatus())) {
            OrderStatus orderStatus = EnumUtils.getEnum(OrderStatus.class, request.getOrderStatus());
            Assert.notNull(orderStatus, RespStatus.PARAM_ERROR,
                    "订单状态值错误, orderStatus=%s", request.getOrderStatus());
            queryCondition.setOrderStatus(orderStatus);
        }

        Pagination pagination = new Pagination(request.getPage(), request.getSize());
        List<OrderInfo> orderInfoList = orderInfoRepository.queryByCondition(queryCondition, pagination);
        List<OrderInfoDTO> list = DefaultUtils.defaultList(orderInfoList)
                .stream().map(OrderConverter::toOrderInfoDTO)
                .collect(Collectors.toList());
        return RespData.success(new PageData<>(list, pagination.getCount()));
    }
}
