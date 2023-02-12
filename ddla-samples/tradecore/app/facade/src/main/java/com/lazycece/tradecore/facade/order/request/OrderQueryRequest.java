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

package com.lazycece.tradecore.facade.order.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author lazycece
 * @date 2023/2/12
 */
@Getter
@Setter
public class OrderQueryRequest implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -4383329900461359499L;

    @NotBlank(message = "userId不能为空")
    private String userId;
    @NotBlank(message = "orderId不能为空")
    private String orderId;
}
