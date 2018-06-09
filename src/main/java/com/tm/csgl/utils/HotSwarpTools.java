package com.tm.csgl.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URLClassLoader;
import java.util.HashSet;

public class HotSwarpTools extends URLClassLoader {

    private static Log log = LogFactory.getLog(HotSwarpTools.class);
    private String basedir; // 需要该类加载器直接加载的类文件的基目录
    private HashSet swapClass; // 需要由该类加载器直接加载的类名

    public HotSwarpTools(String basedir, String[] clazns) {
        super(null); // 指定父类加载器为 null
        this.basedir = basedir;
        this.swapClass = new HashSet();
        loadClassByMe(clazns);
    }

    private void loadClassByMe(String[] clazns) {
        for (int i = 0; i < clazns.length; i++) {
            loadDirectly(clazns[i]);
            this.swapClass.add(clazns[i]);
        }
    }

    private Class loadDirectly(String name) {
        Class cls = null;
        StringBuffer sb = new StringBuffer(basedir);
        String classname = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + classname);
        File classF = new File(sb.toString());
        try {
            cls = instantiateClass(name, new FileInputStream(classF),
                    classF.length());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cls;
    }

    private Class instantiateClass(String name, InputStream fin, long len) {
        byte[] raw = new byte[(int) len];
        try {
            fin.read(raw);
            fin.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return defineClass(name, raw, 0, raw.length);
    }

    protected Class loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        Class cls = null;
        cls = findLoadedClass(name);
        if (!this.swapClass.contains(name) && cls == null)
            cls = getSystemClassLoader().loadClass(name);
        if (cls == null)
            throw new ClassNotFoundException(name);
        if (resolve)
            resolveClass(cls);
        return cls;
    }
}