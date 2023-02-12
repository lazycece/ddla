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

package com.lazycece.tradecore.facade.order.api;

import com.lazycece.rapidf.restful.response.RespData;
import com.lazycece.tradecore.facade.order.request.OrderCancelRequest;
import com.lazycece.tradecore.facade.order.request.OrderCreateRequest;
import com.lazycece.tradecore.facade.order.result.OrderCreateResult;

/**
 * @author lazycece
 * @date 2023/2/11
 */
public interface OrderCommandFacade {

    RespData<OrderCreateResult> createOrder(OrderCreateRequest request);

    RespData<Void> cancelOrder(OrderCancelRequest request);
}
