package com.tm.csgl.domain.fortest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Captain Wang on 18/5/26.
 */
public class FileAndDirectoryList {
    List<String> dir = new ArrayList<String>();

    List<String> file = new ArrayList<String>();
    public FileAndDirectoryList() {

    }

    public FileAndDirectoryList(List<String> dir, List<String> filename) {
        this.dir = dir;
        this.file = filename;
    }

    public void addFile(String fileName){
        this.file.add(fileName);
    }

    public void removeFile(String fileName){
        Iterator<String> it = this.file.iterator();
        while(it.hasNext()){
            String x = it.next();
            if(x.equals(fileName)){
                it.remove();
            }
        }
    }

    public void addDir(String dirName){
        this.dir.add(dirName);
    }

    public void removeDir(String dirName){
        Iterator<String> it = this.dir.iterator();
        while(it.hasNext()){
            String x = it.next();
            if(x.equals(dirName)){
                it.remove();
            }
        }
    }

    public List<String> getDir() {
        return dir;
    }

    public void setDir(List<String> dir) {
        this.dir.addAll(dir);
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> filename) {
        this.file.addAll(filename);
    }
}
