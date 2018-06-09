package com.tm.csgl.service;

import com.tm.csgl.domain.fortest.CodeComplieInfo;
import com.tm.csgl.domain.fortest.CompileTaskDto;
import com.tm.csgl.domain.fortest.TaskResultRedisTool;
import com.tm.csgl.utils.TaskStatusRedisTools;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Captain Wang on 18/5/26.
 */
public class CodeCompile implements Runnable {

    private static Log log = LogFactory.getLog(CodeCompile.class);

    CodeComplieInfo codeComplieInfo = new CodeComplieInfo();
    List<String> flieName = new ArrayList<String>();

    List<String> passFile = new ArrayList<String>();
    List<String> erroFile = new ArrayList<String>();
    private String taskName;

    String options = "";
    String junitLibPath = "";

    CompileTaskDto compileTaskDto = new CompileTaskDto();


    public CodeCompile() {
    }

    public CodeCompile(String taskName, String options, String junitLibPath, List<String> flieName) {

        this.flieName.addAll(flieName);
        this.options = options;
        this.junitLibPath = junitLibPath;
        compileTaskDto.setTotal((long) flieName.size());
        compileTaskDto.setTaskName("Compile");
        this.taskName = taskName;
    }

    public void codeCompiler() {

        TaskStatusRedisTools taskStatusRedisTools = new TaskStatusRedisTools(this.taskName);

        taskStatusRedisTools.setCompiliTaskStatus(new CompileTaskDto());
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        TaskResultRedisTool taskResultRedisTool =new  TaskResultRedisTool(this.taskName);
        log.info("编译任务名称：" + this.taskName);
        log.info("开始编译......");

        for (int i = 0; i < this.flieName.size(); i++) {

            compileTaskDto.setRunningName(flieName.get(i));
            taskStatusRedisTools.setCompiliTaskStatus(compileTaskDto);

            log.info("编译第 " + (i + 1) + "/" + flieName.size() + "个文件: " + flieName.get(i).split("/")[flieName.get(i).split("/").length - 1]);

            int flag = compiler.run(null, null, null, this.options, this.junitLibPath, this.flieName.get(i));
            if (flag == 0) {
                this.passFile.add(flieName.get(i));
                log.info("编译成功!\n");
            } else {
                this.erroFile.add(flieName.get(i));
                log.error("编译失败!\n");
            }
            compileTaskDto.setFinished(compileTaskDto.getFinished() + 1);
            taskStatusRedisTools.setCompiliTaskStatus(compileTaskDto);

        }
        compileTaskDto.setIsFinished(true);

        codeComplieInfo.setPassfile(this.passFile);
        codeComplieInfo.setErrofile(this.erroFile);
        taskStatusRedisTools.setCompiliTaskStatus(compileTaskDto);
        taskResultRedisTool.setComplieResultDetails(codeComplieInfo);
        //return codeComplieInfo;

    }

    @Override
    public void run() {

        codeCompiler();


    }
/*
    public void toCompile(){
        System.out.println("准备编译文件列表：");
        for(String name:this.flieName){
            System.out.println(name);
        }

        try{
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(flieName);
            Iterable<String> options = Arrays.asList("-cp", "/Users/wangwei/Desktop/IntelliJ/gitIdea/csdstest/lib/junit-4.12.jar");
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,diagnostics, options, null, compilationUnits);

            boolean success = task.call();

            fileManager.close();

            System.out.println((success)?"编译成功":"编译失败");
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    */

}
