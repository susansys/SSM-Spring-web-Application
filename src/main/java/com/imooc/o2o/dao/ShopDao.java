package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
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
