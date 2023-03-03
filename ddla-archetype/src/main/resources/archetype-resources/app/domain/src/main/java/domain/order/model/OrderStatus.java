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

package ${package}.domain.order.model;

import com.lazycece.rapidf.domain.model.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lazycece
 * @date 2023/2/11
 */
@Getter
@AllArgsConstructor
public enum OrderStatus implements BaseEnum<String> {

    WAIT_PAY("WAIT_PAY", "待支付"),
    IN_PAY("IN_PAY", "支付中"),
    PAY_SUCCESS("PAY_SUCCESS", "支付成功"),
    PAY_FAIL("PAY_FAIL", "支付失败"),
    SHIPPED("SHIPPED", "已发货"),
    REFUNDED("REFUNDED", "已退款"),
    ;

    private final String code;
    private final String desc;
}
