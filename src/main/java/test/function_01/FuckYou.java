package test.function_01;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FuckYou {
    private static Log log = LogFactory.getLog(FuckYou.class);
    @Test
    public void sayFuckYou(){

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        Date day=new Date();
        Thread th=Thread.currentThread();
        Random rand = new Random();
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"+"_"+th.getId()+"_"+rand.nextInt(100));

        System.out.println( df.format(day)+"_"+th.getId()+"_"+rand.nextInt(100));


        System.out.println("Tread name:"+th.getId());
        log.info("Fuck You!");
    }


    @Test
    public void sayFuckYou_35(){

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        Date day=new Date();
        Thread th=Thread.currentThread();
        Random rand = new Random();
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"+"_"+th.getId()+"_"+rand.nextInt(100));

        System.out.println( df.format(day)+"_"+th.getId()+"_"+rand.nextInt(100));


        System.out.println("Tread name:"+th.getId());
        log.info("Fuck You!");
    }

}
