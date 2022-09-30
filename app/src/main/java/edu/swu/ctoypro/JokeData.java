package edu.swu.ctoypro;

import com.google.gson.annotations.SerializedName;

public class JokeData {
    @SerializedName("Joke")
    private String joke;

    public String getJoke() {
        return joke;
    }
    public void setMessage(String joke) {
        this.joke = joke;
    }
}
