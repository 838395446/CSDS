package com.tm.csgl.domain.configure;

import java.util.Map;

public class TestServerEnvronmentDto {

    private String baseUrl;

    private Map<String,EnvironmentForTestDto> environmentForTestDtoList;

    public TestServerEnvronmentDto(String baseUrl, Map<String, EnvironmentForTestDto> environmentForTestDtoList) {
        this.baseUrl = baseUrl;
        this.environmentForTestDtoList = environmentForTestDtoList;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Map<String, EnvironmentForTestDto> getEnvironmentForTestDtoList() {
        return environmentForTestDtoList;
    }

    public void setEnvironmentForTestDtoList(Map<String, EnvironmentForTestDto> environmentForTestDtoList) {
        this.environmentForTestDtoList = environmentForTestDtoList;
    }

    @Override
    public String toString() {
        return "TestEnvronmentDto{" +
                "baseUrl='" + baseUrl + '\'' +
                ", environmentForTestDtoList=" + environmentForTestDtoList +
                '}';
    }
}
