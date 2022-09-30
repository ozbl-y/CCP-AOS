package edu.swu.ctoypro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import edu.swu.ctoypro.databinding.ActivityMainBinding;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ProgressBar progressBar;
    JokeService jokeService;
    TextView responseText;
    ImageView img;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //기존 setContentView를 제거함
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //바인딩 클래스의 인스턴스 생성
        setContentView(binding.getRoot()); //getRoot메서드로 레이아웃 내부의 최상의 뷰의 인스턴스를 활용하여 생성된 뷰를 액티비티에 표시

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();

        //JokeService jokeService = ApiClient.getClient().create(JokeService.class);
        JokeService jokeService = retrofit.create(JokeService.class);
        jokeService.getJoke("joke").enqueue(new Callback<JokeData>() {
            @Override
            public void onResponse(Call<JokeData> call, Response<JokeData> response) {
                Log.d("MainActivity", jokeService.toString()); //카테고리 불러오기

                //프로그레스바, 반투명효과 지우기
            }

            @Override
            public void onFailure(Call<JokeData> call, Throwable t) {

            }
        });
        
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //카테고리에 해당하는 joke 불러오기
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

/*
        //어댑터 생성
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.joke_category,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //드롭다운뷰 연결
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //UI와 연결
        binding.homeSpinner.setAdapter(adapter);


        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, jokeList);*/

/*    public void spinnerListener(){
        binding.homeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.homeTxt.setText(jokeList.get(position) + "조크조크");
            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }*/

/*progressBar = new ProgressBar(this);
        progressBar.getProgress();
        responseText = binding.homeTxt;*/
