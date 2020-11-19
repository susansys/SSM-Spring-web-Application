package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     *  query shop pagination, input conditions including: shop name, shop status, shop type,
     *  area id, owner
     * @param shopCondition 查询条件
     * @param rowIndex 从第几行开始取数据
     * @param pageSize 返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    /**
     * return counts of queryShopList
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    /**
     *search for the shop by using shop id
     * @param shopId shop id
     * @return shop
     */
    Shop queryByShopId(long shopId);

    /**
     * insert shop
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * update shop
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
