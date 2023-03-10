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
import ${package}.facade.order.api.OrderCommandFacade;
import ${package}.facade.order.request.OrderCancelRequest;
import ${package}.facade.order.request.OrderCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lazycece
 * @date 2023/2/11
 */
@RestController
@RequestMapping("/order")
public class OrderCommandController {

    @Autowired
    private OrderCommandFacade commandFacade;

    @PostMapping("/create")
    public RespData<?> createOrder(@RequestBody @Validated OrderCreateRequest request) {
        return commandFacade.createOrder(request);
    }

    @PostMapping("/cancel")
    public RespData<?> cancelOrder(@RequestBody @Validated OrderCancelRequest request) {
        return commandFacade.cancelOrder(request);
    }
}
