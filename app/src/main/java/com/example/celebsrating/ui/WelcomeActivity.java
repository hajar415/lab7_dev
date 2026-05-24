package com.example.celebsrating.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.celebsrating.R;

public class WelcomeActivity extends AppCompatActivity {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        logo = findViewById(R.id.logo);

        logo.animate().rotation(360f).setDuration(2000);
        logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);
        logo.animate().translationYBy(1000f).setDuration(2000);
        logo.animate().alpha(0f).setDuration(6000);

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                startActivity(new Intent(WelcomeActivity.this,
                        CelebsActivity.class));
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}