package com.imooc.o2o.enums;

import com.imooc.o2o.entity.Shop;

public enum ShopStateEnum {
    CHECK(0, "in review"),
    OFFLINE(-1, "invalid"),
    SUCCESS(1, "success"),
    PASS(2, "pass review"),
    INNER_ERROR(-1001, "inner program error"),
    NULL_SHOPID(-1002, "empty Shop id"),
    NULL_SHOP(-1003, "null shop info");

    private int state;
    private String stateInfo;

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * According to state, return enum value
     */
    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
