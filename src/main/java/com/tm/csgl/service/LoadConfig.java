package com.tm.csgl.service;

import com.google.gson.Gson;
import com.tm.csgl.dao.JedisConfigureDao;
import com.tm.csgl.dao.TestFrameEnvDao;
import com.tm.csgl.domain.configure.JavaPathDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

public class LoadConfig {
    private static Log log = LogFactory.getLog(RunTestCase.class);
    private JavaPathDto javaPathDto;

    @Autowired
    private JedisConfigureDao jedisConfigureDao;

    @Autowired
    TestFrameEnvDao testFrameEnvDao;

    public LoadConfig() {
    }

    public JavaPathDto getJavaPathDto() {

        Jedis jedis = new Jedis(jedisConfigureDao.getHost());
        Gson gson = new Gson();
        log.error("取出jedis："+jedis.get(testFrameEnvDao.getEnvKey()));
        javaPathDto=gson.fromJson(jedis.get(testFrameEnvDao.getEnvKey()),JavaPathDto.class);
        jedis.close();
        return javaPathDto;
    }

}
