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

package ${package}.domain.order.event;

import com.lazycece.rapidf.domain.event.DomainEvent;
import com.lazycece.rapidf.domain.event.handler.DomainEventHandler;
import com.lazycece.rapidf.domain.event.handler.EventHandler;
import ${package}.domain.order.model.OrderStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lazycece
 * @date 2023/3/18
 */
@Slf4j
@EventHandler(type = "${package}.domain.order.event.OrderEventModel")
public class OrderCreateEventHandler implements OrderEventHandler, DomainEventHandler {

    @Override
    public boolean accept(DomainEvent event) {
        OrderEventModel eventModel = event.getData(OrderEventModel.class);
        return eventModel.getOrderStatus() == OrderStatus.WAIT_PAY;
    }

    @Override
    public void handle(DomainEvent event) {
        log.info("================== order create event handler");
    }
}
