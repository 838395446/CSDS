package com.tm.csgl.domain.configure;

public class EnvironmentForTestDto {

    private String interfaceUrl;
    private String userName;
    private String passWord;

    public EnvironmentForTestDto() {
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "EnvironmentForTestDto{" +
                "interfaceUrl='" + interfaceUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
