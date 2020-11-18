package com.imooc.o2o.util;

public class PathUtil {
    private static String separator = System.getProperty("file.separator");

    /**
     * get the base path by OS system.
     * @return base by win or Linux
     */
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath = "F:/Class-Project Resources/image SSMSrping/image";
        } else{
            basePath = "/home/sunsys/image";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    /**
     *
     * @param shopId the shop Id
     * @return the shop image path.
     */
    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}
