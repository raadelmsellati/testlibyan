package com.example.testlibyan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAtbeg() {

    }

    public void BtnClicked(View view){
        TextView txtWelcome = findViewById(R.id.txtwelcome);
        txtWelcome.setText("Hello Raad");
    }
}
