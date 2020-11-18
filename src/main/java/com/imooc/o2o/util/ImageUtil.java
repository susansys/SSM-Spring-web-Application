package com.imooc.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
     * @param cFile CommonsMultipartFile
     * @return newFile
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
     * @param thumbnailInputStream Input stream
     * @param targetAddr the target address of the img.
     * @return relativeAddr, created img's relative path
     */
    public static String generateThumbnail(InputStream thumbnailInputStream, String fileName, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is: " + relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete is: " + PathUtil.getImgBasePath() + relativeAddr);

        try {
            Thumbnails.of(thumbnailInputStream).size(200, 200)
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
     * @param targetAddr the target address of the img.
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
     * @param fileName input file
     * @return fileExtension
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * create random file name, yyyyMMddHHmmss+random5digits
     * @return the random file name
     */
    public static String getRandomFileName() {
        //get random 5 digits
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * storePath is or not the file path
     * if storePath is the file path, delete the file
     * if storePath is the directory, delete all files under this directory.
     * @param storePath the path of storing file.
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if(fileOrPath.isDirectory()) {
                File files[] = fileOrPath.listFiles();
                for(int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("F:/Class-Project Resources/image SSMSrping/xiaohuangren.jpg"))
                .size(200,200).watermark(Positions.BOTTOM_RIGHT, ImageIO
                .read(new File(basePath + "/watermark.jpg")),0.25f)
                .outputQuality(0.8f)
                .toFile("F:/Class-Project Resources/image SSMSrping/xiaohuangrennew.jpg");
    }
}
