package edu.swu.ctoypro;
import static edu.swu.ctoypro.GitHubService.httpLoggingInterceptor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
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

    private ActivityMainBinding binding;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //기존 setContentView를 제거함

        final ProgressBar pb;
        Spinner spinner;
        Button btn;
        TextView txt;
        ImageView img;

        //바인딩 클래스의 인스턴스 생성
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //getRoot메서드로 레이아웃 내부의 최상의 뷰의 인스턴스를 활용하여 생성된 뷰를 액티비티에 표시
        setContentView(binding.getRoot());

        //어댑터 생성
        /*ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(R.array.joke_category);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);*/

        spinnerListener();


        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);

        gitHubService.getMyRepos("userName").enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {

            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });
        
    }


    private HttpLoggingInterceptor httpLoggingInterceptor(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                android.util.Log.e("MyGitHubData :", message + "");
            }
        });

        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    //Spinner Listener
    public void spinnerListener(){
        binding.spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

}


//어댑터 생성
//ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, sp);
//드롭다운뷰 연결
//adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
//UI와 연결
//binding.spinner.setAdapter(adapter);

/*        ArrayList<String> list = new ArrayList<>(Arrays.asList(new String[]{"random","animal","career","celebrity",
               "dev","explicit","fashion","food","history", "money","movie","music","political","religion",
                "science","sport","travel"}));
//        final String[] list = {"random","animal","career","celebrity", "dev","explicit","fashion","food","history",
//                "money","movie","music","political","religion", "science","sport","travel"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
//        spinner.setAdapter(adapter);*/
