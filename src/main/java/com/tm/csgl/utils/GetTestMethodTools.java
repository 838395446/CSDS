package com.tm.csgl.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;

public class GetTestMethodTools {
    private static Log log = LogFactory.getLog(GetTestMethodTools.class);
    private String classDir;
    private String className;

    public GetTestMethodTools() {
    }

    public GetTestMethodTools(String className, String classDir) {
        this.classDir = classDir;
        this.className = className;
    }

    public void getMethodByClass() {
        Method[] method = null;
        try {

            //log.info("获取当前user.dir：" + System.getProperty("user.dir"));

            log.error("热替换开始！~~~");

            String url = System.getProperty("user.dir") + "/src/main/java";
            log.error(url);
            HotSwarpTools cl = new HotSwarpTools(url,
                    new String[]{"test.function_01.FuckYou"});
            Class cls = cl.loadClass("test.function_01.FuckYou");
            Object ob = cls.newInstance();
            // 被调用函数的参数
            method = ob.getClass().getDeclaredMethods();
            for (Method m : method) {
                log.error(m.getClass().getName());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
