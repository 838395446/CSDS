package com.tm.csgl.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    private static Log log = LogFactory.getLog(Junit4Runner.class);

    private List<String> javaFile = new ArrayList<String>();
    private List<String> classFile = new ArrayList<String>();

    private String searchPath = "";

    public Searcher(String searchPath) {
        this.searchPath = searchPath;
        searchFile(this.searchPath);
    }

    public Searcher() {

    }

    public void searchFile(String searchPath) {
        log.info("搜索目录: "+searchPath);
        //String thisPath=this.packagePath;
        File file = new File(searchPath);
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {

            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();

                if (fileName.contains(".java")) {
                    log.info("发现 Java 文件: " + fileName);
                    javaFile.add(searchPath+"/"+fileName);
                }
                if (fileName.contains(".class")) {
                    log.info("发现 Class 文件: " + fileName);
                    classFile.add(searchPath+"/"+fileName);
                }
            }

            if (fileList[i].isDirectory()) {

                searchFile(searchPath+"/"+fileList[i].getName());
            }
        }

    }

    public List<String> getJavaFile() {

        return javaFile;
    }

    public void setJavaFile(List<String> javaFile) {

        this.javaFile = javaFile;
    }

    public List<String> getClassFile() {
        return classFile;
    }

    public void setClassFile(List<String> classFile) {
        this.classFile = classFile;
    }

    public String getSearchPath() {
        return searchPath;
    }

    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    }

}
