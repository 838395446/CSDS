package com.tm.csgl.dao;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "testframeev")
public class TestFrameEnvDao {

    private String envKey;

    public TestFrameEnvDao() {
    }

    public TestFrameEnvDao(String envKey) {

        this.envKey = envKey;
    }

    public String getEnvKey() {
        return envKey;
    }

    public void setEnvKey(String envKey) {
        this.envKey = envKey;
    }

    @Override
    public String toString() {
        return "TestFrameEvDao{" +
                "envKey='" + envKey + '\'' +
                '}';
    }
}
