package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptions.ShopOperationException;

import java.io.InputStream;

public interface ShopService {
    /**
     * get shop info by using shop id.
     * @param shopId shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * modify shop info, including image execution
     * @param shop shop
     * @param shopImgInputStream shop image
     * @param fileName file name
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
    /**
     * register shop info, including img execution.
     * @param shop shop
     * @param shopImgInputStream shop image
     * @param fileName shop image filename
     * @return
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
