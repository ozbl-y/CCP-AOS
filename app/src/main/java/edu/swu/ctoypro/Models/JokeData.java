package edu.swu.ctoypro.Models;

import android.renderscript.Sampler;

public class JokeData {
    public String icon_url;
    public String value;

    public JokeData(String icon_url, String value){
        this.icon_url = icon_url;
        this.value = value;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public String getValue() {
        return value;
    }
}
