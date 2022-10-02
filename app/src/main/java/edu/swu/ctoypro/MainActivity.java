package edu.swu.ctoypro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

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
    List<String> jokelist;
    String jokevalue;
    TextView responseText;
    Spinner spinner;
    ProgressBar progressBar;
    ImageButton imgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //기존 setContentView를 제거함
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //바인딩 클래스의 인스턴스 생성
        setContentView(binding.getRoot()); //getRoot메서드로 레이아웃 내부의 최상의 뷰의 인스턴스를 활용하여 생성된 뷰를 액티비티에 표시

        jokelist = new ArrayList<>();

        responseText = binding.homeTxt;
        Spinner spinner = binding.homeSpinner;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jokelist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
//        ProgressBar mDialog = new ProgressBar(MainActivity.this);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();

        JokeService jokeService = ApiClient.getClient().create(JokeService.class);

        jokeService.getJoke().enqueue(new Callback<List<String>>() { // 리스트값 받아옴
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> jokelist = response.body();
                Log.d("category", jokelist.toString());
                try {
                    //Log.v("value", responseText.toString());

                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

        jokeService.getJokeData("value").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Call<String> getJokeData(@Query("category") String category);
                //List<String> jokelist = response.body();
                Log.d("value", responseText.toString());
                try {
                    //Log.v("value", responseText.toString());

                } catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        binding.homeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // responseText.setText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.imgBtn.setOnClickListener(new View.OnClickListener() {
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