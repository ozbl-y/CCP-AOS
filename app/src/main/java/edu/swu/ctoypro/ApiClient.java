package edu.swu.ctoypro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static String BASE_URL = "https://api.chucknorris.io/";

    private static Retrofit retrofit;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //통신 완료 후, 어떤 converter로 데이터를 parsing할 것인지
                    .build();
        }
        return retrofit;
    }
}
