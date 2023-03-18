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

package ${package}.adapter.web.order;

import com.lazycece.rapidf.restful.response.RespData;
import ${package}.facade.order.api.OrderQueryFacade;
import ${package}.facade.order.request.OrderListQueryRequest;
import ${package}.facade.order.request.OrderInfoQueryRequest;
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
public class OrderQueryController {

    @Autowired
    private OrderQueryFacade queryFacade;

    @GetMapping("/query")
    public RespData<?> queryOrder(@Validated OrderInfoQueryRequest request) {
        return queryFacade.queryOrder(request);
    }

    @GetMapping("/queryList")
    public RespData<?> queryList(@Validated OrderListQueryRequest request) {
        return queryFacade.queryOrderList(request);
    }
}
