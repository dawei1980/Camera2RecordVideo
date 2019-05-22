package com.record.video.utils;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * SD卡文件创建
 **/
public class SDFileEstablish {

    public static SDFileEstablish sdFileEstablish = null;

    public static SDFileEstablish getsdFileEstablish(){
        if(sdFileEstablish ==null){
            sdFileEstablish = new SDFileEstablish();
        }
        return sdFileEstablish;
    }
    public SDFileEstablish(){}
    /**
     * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
     * @return
     */
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public String getFileName(boolean isPicture) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HH_mm_ss", Locale.CHINA);
        String datetime =  formatter.format(new Date(System.currentTimeMillis()));
        //若是图片
        if (isPicture) {
            return "/IMG_" + datetime + ".jpg";
        } else {
            //若是视频
            return "/VID_" + datetime + ".mp4";
//            return "/VID_" + datetime + ".3gp";
        }
    }

    /**
     * 图片和视频返回路径
     * @param isformat
     * @return
     */
    public File file2(boolean isformat){
        String mPath = foldername(isformat);
        String path  = foldertimeneme(mPath);
        String mpath2 = getFileName(isformat);
        File f2 = new File(path+mpath2);
        return f2;
    }

    /**
     * 图片和视频文件夹命名
     * @param isformat
     * @return
     */
    public String folder(boolean isformat){
        if(isformat){
            return "/photo";
        }else {
            return "/video";
        }
    }

    /**
     * yyyy-MM-dd 格式的时间
     * @return
     */
    public String foldertime(){
        return new SimpleDateFormat("yyyy-MM-dd").format((new Date()));
    }

    /**
     * 创建文件夹
     * @param isformat
     * @return
     */
    public String foldername(boolean isformat){
        String sdpath = mediaresources()+folder(isformat);
        return file(sdpath);
    }
    /**
     * 创建媒体资源文件夹
     */
    public String mediaresources(){
        return file(SDCard.SDcardpath()+"/media");

//        File saveFile = new File(Environment.getExternalStorageDirectory(), "video");
//        return file();
    }

    /**
     * 创建时间文件夹
     * @param path
     * @return
     */
    public String foldertimeneme(String path){
        return file(path+"/"+foldertime());
    }
    /**
     * 判断文件夹是否创建
     */
    public String file(String str){
        File file = new File(str);
        if(!file.exists()){
            file.mkdir();
        }
        return file.toString();
    }

}
