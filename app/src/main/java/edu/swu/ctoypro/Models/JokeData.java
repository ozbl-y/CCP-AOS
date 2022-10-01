package edu.swu.ctoypro.Models;

import android.renderscript.Sampler;

public class JokeData {
    private String icon_url;
    private String value;

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /*public JokeData(String icon_url, String value){
        this.icon_url = icon_url;
        this.value = value;
    }

    }*/
}
