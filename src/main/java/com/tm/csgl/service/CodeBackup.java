package com.tm.csgl.service;

import javax.tools.*;
import java.io.*;
import java.util.Arrays;

/**
 * Created by Captain Wang on 18/5/25.
 */
public class CodeBackup {




    /*
    * 读取指定路径下的文件名和目录名
    */
    public void getFileList() {
        //   File file = new File("/Users/wangwei/Desktop/case/src");
        File file = new File("./src/test/java");

        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                System.out.println("文件：" + fileName);
            }

            if (fileList[i].isDirectory()) {
                String fileName = fileList[i].getName();
                System.out.println("目录：" + fileName);
            }
        }
    }


    public void runJavaFiles() throws Exception {
        try {

            /**
             *

             //String shpath="./case/Test01.java";
             String javaFilePath="javac ./case/Test01.java";
             Process ps = Runtime.getRuntime().exec(javaFilePath);
             ps.waitFor();

             BufferedReader br_1 = new BufferedReader(new InputStreamReader(ps.getInputStream()));
             StringBuffer runTmp_1 = new StringBuffer();
             String line_1;
             while ((line_1 = br_1.readLine()) != null) {
             runTmp_1.append(line_1).append("\n");
             }
             String result_1 = runTmp_1.toString();
             System.out.println(result_1);



            //Class nc= Class.forName("./case/Test01");
            String javacFilePath="javac -cp ../../../lib/junit-4.12.jar ./src/test/java/Test01.ll";
            //String javacFilePath=("ps aux");
            Process ps1 = Runtime.getRuntime().exec(javacFilePath);
            ps1.waitFor();

            BufferedInputStream in = new BufferedInputStream(ps1.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s;
            while ((s = br.readLine()) != null){
                System.out.println(s);
            }
*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }




        /*动态加载指定类
        File file=new File("./case");//类路径(包文件上一层)
        URL url=file.toURI().toURL();
        ClassLoader loader=new URLClassLoader(new URL[]{url});//创建类加载器
        //import com.sun.org.apache.bcel.internal.util.ClassLoader;
        //ClassLoader classLoader = new ClassLoader(new String[]{""});//类路径
        Class<?> cls=loader.loadClass("Test01");//加载指定类，注意一定要带上类的包名
        Object obj=cls.newInstance();//初始化一个实例
        Method method[]=cls.getMethods();

        for (Method m:method){

            System.out.println("Method name is : "+m.getName());
        }

        //Method method=cls.getMethod("printString",String.class,String.class);//方法名和对应的参数类型
        //Object o=method.invoke(obj,"chen","leixing");//调用得到的上边的方法method
        System.out.println("Class name is : "+obj.getClass().getName());//输出"chenleixing"

*/
    }

    public void codeCompiler(){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        System.out.println("开始编译......");
        //String op ="-cp ../../../lib/junit-4.12.jar";
        //InputStream cin =op;

        //int flag = compiler.run(null, null, null,"-cp","/Users/wangwei/Desktop/IntelliJ/gitIdea/csdstest/lib/junit-4.12.jar","./src/test/java/pg1/group01/Test02.java");
        try{

            int flag = compiler.run(null, null, null,"-cp","./lib/junit-4.12.jar","./src/test/java/pg1/group01/Test01.java");

            System.out.println(flag == 0 ? "编译成功" : "编译失败");
        }catch (Exception e){
            System.out.println("编译错误信息：");
            e.printStackTrace();

        }




    }

    public void compilerWithParameter() throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 建立DiagnosticCollector对象
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        // 建立用于保存被编译文件名的对象
        // 每个文件被保存在一个从JavaFileObject继承的类中
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList("./src/test/java/pg1/group01/Test02.java","./src/test/java/pg1/group01/Test01.java","./src/test/java/pg1/group01/Test03.java"));
        Iterable<String> options = Arrays.asList("-cp", "./lib/junit-4.12.jar");
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,diagnostics, options, null, compilationUnits);
        // 编译源程式

        boolean success = task.call();
        fileManager.close();
        System.out.println((success)?"编译成功":"编译失败");
    }

/**
    public void runJunitClass() {
        try{
            File file=new File("./src/test/java");//类路径(包文件上一层)
            URL url=file.toURI().toURL();
            ClassLoader loader=new URLClassLoader(new URL[]{url});//创建类加载器
            //import com.sun.org.apache.bcel.internal.util.ClassLoader;
            //ClassLoader classLoader = new ClassLoader(new String[]{""});//类路径
            Class<?> cls=loader.loadClass("pg1.group01.Test02");//加载指定类，注意一定要带上类的包名
            Object obj=cls.newInstance();//初始化一个实例
            System.out.println("================================"+obj.getClass().getMethods().length);
            //Result result = JUnitCore.runClasses(obj.getClass());
            for(Method method:obj.getClass().getDeclaredMethods()){
                System.out.println(method.getName().toString());
            }
            //Result result = JUnitCore.;
            Result result = JUnitCore.runClasses(obj.getClass());

            System.out.println("================================");
            System.out.println("执行总数:"+result.getRunCount());
            System.out.println("失败总数:"+result.getFailureCount());
            System.out.println(String.format("运行时间:" + result.getRunTime(), 2018050302));
            // for(String )
            System.out.println("错误信息:"+result.getFailures().getClass().toString());

            for(int i=0;i<result.getFailures().size();i++){
                System.out.println("错误信息:"+result.getFailures().get(i).getException().fillInStackTrace());
            }

            System.out.println("================================");


        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String []arg)throws Exception {


        CodeBackup rf = new CodeBackup();
        rf.compilerWithParameter();
        //rf.codeCompiler();
        rf.getFileList();

        //rf.runJavaFiles();
        rf.runJunitClass();


    }
 **/
}
