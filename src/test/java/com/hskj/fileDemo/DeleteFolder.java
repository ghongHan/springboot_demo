package com.hskj.fileDemo;

import org.junit.Test;

import java.io.File;

/**
 * Created by hongHan_gao
 * Date: 2018/7/30
 * 递归删除文件
 */


public class DeleteFolder {

    @Test
    public void deleteFolderTest() {
        File file = new File("E:/resource");
        //递归删除文件
        if (file.isDirectory()) {
            System.out.println(deleteFolder(file) ? "删除成功!" : "删除失败!");
        }
    }

    /**
     * 删除文件
     * @param file
     * @return
     */
    public boolean deleteFolder(File file) {
        boolean result = true;
        File[] files = file.listFiles();
        if (null != files) {
            for (File f : files) {
                if(f.isDirectory()){
                    deleteFolder(f);
                }else{
                    if(!f.delete()){
                        System.out.println(f + "文件删除失败! ");
                        return false;
                    }
                }
            }
        }
        result = file.delete();
        return result;
    }
}
