package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
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
