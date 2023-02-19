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

package ${package}.application.order.validator;

import com.lazycece.rapidf.restful.Assert;
import com.lazycece.rapidf.restful.response.RespStatus;
import com.lazycece.rapidf.utils.DefaultUtils;
import ${package}.facade.order.request.OrderCreateRequest;

/**
 * @author lazycece
 * @date 2023/2/12
 */
public class OrderCreateRequestValidator {

    public static void validate(OrderCreateRequest request) {
        Assert.notNull(request, RespStatus.PARAM_ERROR, "创建订单请求不能为null");
        Assert.notBlank(request.getUserId(), RespStatus.PARAM_ERROR, "userId不能为空");
        Assert.notEmpty(request.getGoodBuyInfoList(), RespStatus.PARAM_ERROR, "购买商品列表不能为空");
        DefaultUtils.defaultList(request.getGoodBuyInfoList())
                .forEach(goodBuyInfo -> {
                    Assert.notBlank(goodBuyInfo.getGoodsId(), RespStatus.PARAM_ERROR, "goodId不能为空");
                    // 继续校验商品的存在性

                    Assert.notNull(goodBuyInfo.getCount(), RespStatus.PARAM_ERROR, "count不能为null");
                });
    }
}
