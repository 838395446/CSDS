package com.tm.csgl.domain.configure;

public class JavaPathDto {

    private String dependencePath;
    private String casePath;

    public JavaPathDto() {
    }

    public JavaPathDto(String dependencePath, String casePath) {
        this.dependencePath = dependencePath;
        this.casePath = casePath;
    }

    public String getDependencePath() {
        return dependencePath;
    }

    public void setDependencePath(String dependencePath) {
        this.dependencePath = dependencePath;
    }

    public String getCasePath() {
        return casePath;
    }

    public void setCasePath(String casePath) {
        this.casePath = casePath;
    }

    @Override
    public String toString() {
        return "JavaPathDto{" +
                "dependencePath='" + dependencePath + '\'' +
                ", casePath='" + casePath + '\'' +
                '}';
    }
}
