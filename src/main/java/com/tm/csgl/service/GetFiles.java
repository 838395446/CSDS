package com.tm.csgl.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Captain Wang on 18/5/26.
 */
public class GetFiles {
    private static Log log=LogFactory.getLog(GetFiles.class);

    public FileAndDirectoryList getFileList(String path) {
        log.info("获取文件列表，路径：" +path);

        FileAndDirectoryList fd = new FileAndDirectoryList();

        File file = new File(path);
        File[] fileList = file.listFiles();
        List<String> directory = new ArrayList<String>();
        List<String> files = new ArrayList<String>();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                String name = fileName.substring(fileName.length() - 5);
                if (name.contains(".java")) {
                    fd.addFile(fileName);
                }
            }

            if (fileList[i].isDirectory()) {

                String directoryName = fileList[i].getName();
                fd.addDir(directoryName);
            }
        }
        if(fd.getFile().size()>0){
            for(String name:fd.getFile()){
                log.info("找到文件："+name);
            }
            for(String name:fd.getDir()){
                log.info("找到目录："+name);
            }
        }

        return fd;
    }
}
