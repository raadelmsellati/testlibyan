package com.example.testlibyan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // loading Animation from
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // getting the Button from activity_main.xml file
        final ImageView welcome = findViewById(R.id.welcome);
        welcome.startAnimation(animation);
    }

    public void registerDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View registerPopup = getLayoutInflater().inflate(R.layout.popup, null);
    }


    /*public void BtnClicked(View view){
        TextView txtWelcome = findViewById(R.id.txtwelcome);
        txtWelcome.setText("Hello Raad");
    }*/
}
