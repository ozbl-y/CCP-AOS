package edu.swu.ctoypro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import edu.swu.ctoypro.Models.JokeData;
import edu.swu.ctoypro.Models.JokeDataClass;
import edu.swu.ctoypro.databinding.ActivityMainBinding;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    
    ActivityMainBinding binding;
    /*Spinner spinner;
    ProgressBar progressBar;
    JokeService jokeService;
    TextView responseText;
    ImageView img;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //기존 setContentView를 제거함
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //바인딩 클래스의 인스턴스 생성
        setContentView(binding.getRoot()); //getRoot메서드로 레이아웃 내부의 최상의 뷰의 인스턴스를 활용하여 생성된 뷰를 액티비티에 표시


        ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
//        ProgressBar mDialog = new ProgressBar(MainActivity.this);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();


        JokeService jokeService = ApiClient.getClient().create(JokeService.class);
        Call<List<String>> call = jokeService.getJoke();
        jokeService.getJoke().enqueue(new Callback<List<String>>() { // 리스트값 받아옴
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> jokeData = response.body();
                pDialog.dismiss();
                //반투명 사라짐
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

        jokeService.getJokeData("value").enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
        
        binding.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //스피너에서 선택한 value에 맞는 joke로 바뀜
            }
        });

    }

    private HttpLoggingInterceptor httpLoggingInterceptor(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String joke) {
                android.util.Log.e("message", "");
            }
        });

        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
