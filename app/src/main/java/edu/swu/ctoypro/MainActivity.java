package edu.swu.ctoypro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import edu.swu.ctoypro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final String[] list = {"animal","career","celebrity","dev","explicit","fashion","food","history",
                "money","movie","music","political","religion","science","sport","travel"};

       /*반투명처리*/
        Drawable alpha = ((ImageView)findViewById(R.id.img)).getBackground();
        alpha.setAlpha(50);

    }
}