package com.tm.csgl.service;

import com.tm.csgl.utils.Searcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Captain Wang on 18/5/27.
 */
public class GetTestCase {
    private static Log log = LogFactory.getLog(GetTestCase.class);

    private List<String> caseMethod = new ArrayList<String>();
    //private List<String> classFile = new ArrayList<String>();
    private String classPath;

    public GetTestCase() {


    }

    public GetTestCase(String classPath) {
        this.classPath = classPath;
    }

    public List<String> GetTestMethods() {

        Searcher searcher = new Searcher(this.classPath);

        log.info("所有class文件数量：" + searcher.getClassFile().size());

        for (String className : searcher.getClassFile()) {

            log.info("搜索到 Class 文件：" + className);
            try {

                //log.info("目标Root路径：" + this.classPath);

                //GetTestMethodTools getTestMethodTools=new GetTestMethodTools(className,"");

                //getTestMethodTools.getMethodByClass();




                File file = new File(this.classPath.replace("/test",""));//类路径(包文件上一层)
                URL url = file.toURI().toURL();
                ClassLoader loader = new URLClassLoader(new URL[]{url});//创建类加载器
                Class<?> cls = null;//加载指定类，注意一定要带上类的包名
                log.info("获取测试用例，当前文件：" + "test" + className.replace(this.classPath, "").replace("/", "."));
                cls = loader.loadClass("test" + className.replace(this.classPath, "").replace("/", ".").replace(".class", ""));
                Object obj = cls.newInstance();//初始化一个实例



                //getTestMethodTools.getMethodByClass();
                for (Method method : obj.getClass().getDeclaredMethods()) {

                    if(method.isAnnotationPresent(Test.class)){
                        this.caseMethod.add((className.replace(this.classPath, "").replace("/", ".").replace(".class","") + "." + method.getName()).substring(1));
                        Test test=method.getAnnotation(Test.class);
                        log.info("注解值："+test.getClass());
                        log.info(method.getName());
                    }

                }

 /*

                for (Method method : obj..getMethodByClass()) {

                    if(method.isAnnotationPresent(Test.class)){
                        this.caseMethod.add((className.replace(this.classPath, "").replace("/", ".").replace(".class","") + "." + method.getName()).substring(1));
                        Test test=method.getAnnotation(Test.class);
                        log.info("注解值："+test.getClass());
                        log.info(method.getName());
                    }

                }
*/

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return this.caseMethod;

    }


}
