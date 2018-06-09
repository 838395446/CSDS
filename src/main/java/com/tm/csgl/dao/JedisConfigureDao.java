package com.tm.csgl.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "jedisconfig")
public class JedisConfigureDao {

  private String host;
  private Integer port;

  private String username;
  private String password;

  public JedisConfigureDao() {
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }



  @Override
  public String toString() {
    return "JedisConfigureDao{" +
            "host='" + host + '\'' +
            ",port=" + port +
            ",username='" + username + '\'' +
            ",password='" + password + '\'' +
            '}';
  }
}
