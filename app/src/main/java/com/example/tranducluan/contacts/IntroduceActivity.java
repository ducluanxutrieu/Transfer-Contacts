package com.example.tranducluan.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroduceActivity extends AppCompatActivity {
    Button goHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        goHome = findViewById(R.id.buttonBackHome);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroduceActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
