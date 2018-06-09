package com.tm.csgl.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Captain Wang on 18/5/25.
 */
public class RunTestCase<T> {

    private static Log log = LogFactory.getLog(RunTestCase.class);

    private String filePaht;//类路径(包文件上一层)

    private String className;
    private String methodName;

    public RunTestCase() {
    }


    public RunTestCase(String filePaht, String className, String methodName) {

        this.filePaht = filePaht;
        this.className = className;
        this.methodName = methodName;
    }


    public Object getClassObject() {


        try {
            log.info("获取用例文件[ " + this.className + " ]的实体......");
            File file = new File(this.filePaht);
            URL url = file.toURI().toURL();
            ClassLoader loader = new URLClassLoader(new URL[]{url});//创建类加载器
            Class<?> cls = loader.loadClass(this.className);//加载指定类，注意一定要带上类的包名
            Object obj = cls.newInstance();//初始化一个实例
            log.info("已获取。");
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void runTestCaseByClass() {
/**
 *
 * 暂不支持
 */

    }




}
