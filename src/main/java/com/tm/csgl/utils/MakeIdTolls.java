package com.tm.csgl.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MakeIdTolls {
    public String makeRandName(String HeadName){
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        Date day=new Date();
        Thread th=Thread.currentThread();
        Random rand = new Random();
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        return  HeadName+"_"+df.format(day)+"_"+th.getId()+"_"+rand.nextInt(100);
    }
}
