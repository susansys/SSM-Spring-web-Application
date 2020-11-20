package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * query product category by shop id
     * @param shopId shop id
     * @return list of product category
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * batch inset categories
     * @param productCategoryList a list of product categories
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
}
