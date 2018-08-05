package com.example.tranducluan.contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button goToIntroduce;
    ImageButton transferStart;
    TextView doNotAccepPermission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToIntroduce = findViewById(R.id.go_to_guide);
        transferStart = findViewById(R.id.button_start);
        initPermission();
        goToIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IntroduceActivity.class);
                startActivity(intent);
            }
        });

        transferStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
                startActivity(intent);
            }
        });

    }
        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            if (requestCode == 1) {
                if (grantResults.length == 1 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permision Write Contacts is Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Permision Write Contacts is Denied", Toast.LENGTH_SHORT).show();

                }
            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

        public void initPermission () {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                    //Permisson don't granted
                    if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                        Toast.makeText(MainActivity.this, "Permission isn't granted ", Toast.LENGTH_SHORT).show();
                    }
                    // Permisson don't granted and dont show dialog again.
                    else {
                        Toast.makeText(MainActivity.this, "Permisson don't granted and dont show dialog again ", Toast.LENGTH_SHORT).show();
                    }
                    //Register permission
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
                }
                if (checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                    //Permisson don't granted
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                        Toast.makeText(MainActivity.this, "Permission isn't granted ", Toast.LENGTH_SHORT).show();
                    }
                    // Permisson don't granted and dont show dialog again.
                    else {
                        Toast.makeText(MainActivity.this, "Permisson don't granted and dont show dialog again ", Toast.LENGTH_SHORT).show();
                    }
                    //Register permission
                    requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, 1);
                }
            }
        }


    }
