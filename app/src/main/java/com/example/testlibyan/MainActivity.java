package com.example.testlibyan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText register_user, register_password, register_email;
    private Button createB, cancelB;
    private ImageView pp_register;

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

    public void registerDialog(View view) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View registerPopup = getLayoutInflater().inflate(R.layout.popup, null);

        pp_register = (ImageView) registerPopup.findViewById(R.id.ppRegister);
        register_user = (EditText) registerPopup.findViewById(R.id.inputUser);
        register_password = (EditText) registerPopup.findViewById(R.id.inputPassword);
        register_email = (EditText) registerPopup.findViewById(R.id.inputEmail);

        createB = (Button) registerPopup.findViewById(R.id.create);
        cancelB = (Button) registerPopup.findViewById(R.id.cancelCreate);

        dialogBuilder.setView(registerPopup);
        dialog = dialogBuilder.create();
        dialog.show();

        createB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Still need to be connected to DB
            }
        });

        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }



    /*public void BtnClicked(View view){
        TextView txtWelcome = findViewById(R.id.txtwelcome);
        txtWelcome.setText("Hello Raad");
    }*/
}
