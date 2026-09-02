/*
 *    Copyright (C) 2023 lazycece<lazycece@gmail.com>. All rights reserved.
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
package com.lazycece.tradecore.adapter.web.order;

import com.lazycece.rapidf.restful.dto.PageData;
import com.lazycece.rapidf.restful.response.RespData;
import com.lazycece.tradecore.facade.order.api.OrderQueryFacade;
import com.lazycece.tradecore.facade.order.dto.OrderInfoDTO;
import com.lazycece.tradecore.facade.order.request.OrderInfoQueryRequest;
import com.lazycece.tradecore.facade.order.request.OrderListQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lazycece
 * @date 2023/2/11
 */
@RestController
@RequestMapping("/order")
public class OrderQueryController implements OrderQueryFacade {

    @Autowired private OrderQueryFacade queryFacade;

    @Override
    @GetMapping("/query")
    public RespData<OrderInfoDTO> queryOrder(@Validated OrderInfoQueryRequest request) {
        return queryFacade.queryOrder(request);
    }

    @Override
    @GetMapping("/queryList")
    public RespData<PageData<OrderInfoDTO>> queryOrderList(
            @Validated OrderListQueryRequest request) {
        return queryFacade.queryOrderList(request);
    }
}
