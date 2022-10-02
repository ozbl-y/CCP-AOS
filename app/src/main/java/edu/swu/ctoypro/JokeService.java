package edu.swu.ctoypro;

import com.google.gson.JsonObject;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// 내가 어떤 요청을 어떻게 보낼건지 메서드 정의
public interface JokeService {

    @GET("jokes/categories") // 카테고리 List, value값 없음
    Call<List<String>> getJoke();

    @GET("jokes/categories/random") // value값
    Call<String> getJokeData(@Query("value") String value);
    // value값 존재 -> Query 써야 됨


}
