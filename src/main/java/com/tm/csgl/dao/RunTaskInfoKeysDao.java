package com.tm.csgl.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "runtaskinfo")
public class RunTaskInfoKeysDao {
    private String runtaskinfo;

    public RunTaskInfoKeysDao() {
    }

    public String getRuntaskinfo() {
        return runtaskinfo;
    }

    public void setRuntaskinfo(String runtaskinfo) {
        this.runtaskinfo = runtaskinfo;
    }

    @Override
    public String toString() {
        return "RunTaskInfoKeysDao{" +
                "runtaskinfo='" + runtaskinfo + '\'' +
                '}';
    }
}
