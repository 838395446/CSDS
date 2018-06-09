package com.tm.csgl.domain.fortest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Captain Wang on 18/5/26.
 */
public class CodeComplieInfo {

    private List<String> passfile = new ArrayList<String>();

    private List<String> errofile = new ArrayList<String>();


    public List<String> getPassfile() {
        return passfile;
    }

    public void setPassfile(List<String> passfile) {
        this.passfile = passfile;
    }

    public List<String> getErrofile() {
        return errofile;
    }

    public void setErrofile(List<String> errofile) {
        this.errofile = errofile;
    }

    @Override
    public String toString() {
        return "CodeComplieInfo{" +
                "passfile=" + passfile +
                ", errofile=" + errofile +
                '}';
    }
}
