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

package com.lazycece.tradecore.domain.order.model;

import com.lazycece.rapidf.domain.statemachine.StateEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lazycece
 * @date 2023/2/12
 */
@AllArgsConstructor
@Getter
public enum OrderEventAction implements StateEvent<String> {

    ORDER_CREATE("ORDER_CREATE", "订单创建"),
    ORDER_CANCEL("ORDER_CANCEL", "订单取消"),
    ORDER_PAYMENT("ORDER_PAYMENT", "订单支付"),
    PAY_SUCCESS("PAY_SUCCESS", "支付成功"),
    PAY_FAIL("PAY_FAIL", "支付失败"),
    ORDER_SHIP("ORDER_SHIP", "订单发货"),
    ORDER_REFUND("ORDER_REFUND", "订单退款"),
    ;

    private final String code;
    private final String desc;
}
