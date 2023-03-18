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

package ${package}.application.order.handler;

import com.lazycece.rapidf.domain.anotation.ApplicationHandler;
import com.lazycece.rapidf.domain.cqrs.QueryHandler;
import com.lazycece.rapidf.restful.dto.PageData;
import com.lazycece.rapidf.restful.response.RespData;
import ${package}.facade.order.dto.OrderInfoDTO;
import ${package}.facade.order.request.OrderListQueryRequest;

/**
 * @author lazycece
 */
@ApplicationHandler("order.list.query1.0")
public class OrderListQueryHandler implements QueryHandler<RespData<?>, OrderListQueryRequest> {

    @Override
    public RespData<PageData<OrderInfoDTO>> handle(OrderListQueryRequest query) {

        return null;
    }
}