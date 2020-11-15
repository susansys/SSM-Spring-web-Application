package com.imooc.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = "F:/Muke Projects/SSM+Spring/src/main/resources";
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * transfer CommonsMultipartFile to File
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }
    /**
     * solve pressed img, return the new created img's relative path
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(File thumbnail, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is: " + relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete is: " + PathUtil.getImgBasePath() + relativeAddr);

        try {
            Thumbnails.of(thumbnail).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.
                            read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * create the directory of the dest path. /home/work/sunsys/xxx.jpg
     * home, work, sunsys should be created automatically.
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * get input file stream extension
     * @param cFile
     * @return
     */
    private static String getFileExtension(File cFile) {
        String originalFileName = cFile.getName();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * create random file name, yyyyMMddHHmmss+random5digits
     * @return
     */
    private static String getRandomFileName() {
        //get random 5 digits
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("F:/Class-Project Resources/image SSMSrping/xiaohuangren.jpg"))
                .size(200,200).watermark(Positions.BOTTOM_RIGHT, ImageIO
                .read(new File(basePath + "/watermark.jpg")),0.25f)
                .outputQuality(0.8f)
                .toFile("F:/Class-Project Resources/image SSMSrping/xiaohuangrennew.jpg");
    }
}
