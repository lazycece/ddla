#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
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
package ${package}.infra.acl.respository;

import com.google.common.collect.Lists;
import com.lazycece.rapidf.domain.anotation.DomainRepository;
import ${package}.domain.goods.model.Goods;
import ${package}.domain.goods.model.GoodsStatus;
import ${package}.domain.goods.repository.GoodsRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lazycece
 * @date 2023/2/11
 */
@DomainRepository
public class GoodsRepositoryImpl implements GoodsRepository {

    @Override
    public List<Goods> queryByGoodsIdList(List<String> goodsIdList) {
        Goods goods = new Goods();
        goods.setGoodsId("goodsId");
        goods.setName("name");
        goods.setPrice(BigDecimal.valueOf(8.88));
        goods.setGoodsStatus(GoodsStatus.ONLINE);
        goods.setCreator("124");
        goods.setUpdater("124");
        goods.setCreateTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        goods.setDeleted(false);
        return Lists.newArrayList(goods);
    }
}
