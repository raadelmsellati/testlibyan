package com.example.testlibyan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText register_user, register_password, register_email;
    private Button createB, cancelB;
    private ImageView pp_register;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // loading Animation from
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // getting the Button from activity_main.xml file
        final ImageView welcome = findViewById(R.id.welcome);
        final Button loginB = findViewById(R.id.button_id);
        final Button menuB = findViewById(R.id.button_id2);
        final TextView register = findViewById(R.id.textView);

        welcome.startAnimation(animation);
        loginB.startAnimation(animation);
        menuB.startAnimation(animation);
        register.startAnimation(animation);

    }

    //Register Dialog
    //-------------------------------------------
    public void registerDialog(View view) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View registerPopup = getLayoutInflater().inflate(R.layout.popup, null);

        mAuth = FirebaseAuth.getInstance();

        register_user = (EditText) registerPopup.findViewById(R.id.inputUser);
        register_password = (EditText) registerPopup.findViewById(R.id.inputPassword);
        register_email = (EditText) registerPopup.findViewById(R.id.inputEmail);

        createB = (Button) registerPopup.findViewById(R.id.create);
        cancelB = (Button) registerPopup.findViewById(R.id.cancelCreate);

        //Will make the temp pp here instead of ppRegister -> ppTemp
        pp_register = (ImageView) registerPopup.findViewById(R.id.ppTemp);
        //Will make the temp pp here

        dialogBuilder.setView(registerPopup);
        dialog = dialogBuilder.create();
        dialog.show();

        createB.setOnClickListener(new View.OnClickListener() {
            @Override
            //When the user click create
            public void onClick(View view) {
                //Still need to be connected to DB
                String email = register_email.getText().toString().trim();
                String username = register_user.getText().toString().trim();
                String password = register_password.getText().toString().trim();

                if(email.isEmpty())
                {
                    register_email.setError("Email is required!");
                    register_email.requestFocus();
                    return;
                }
                if(username.isEmpty())
                {
                    register_user.setError("Username is required");
                    register_user.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    register_password.setError("Password is required");
                    register_user.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    register_email.setError("Email is not valid");
                    register_email.requestFocus();
                    return;
                }
                if(password.length() < 6)
                {
                    register_password.setError("Password should be at least 6 characters");
                    register_password.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()) {
                                    User user = new User(username, email);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(MainActivity.this,"User has been registered successfully",Toast.LENGTH_LONG).show();
                                                        progressBar.setVisibility(View.VISIBLE);
                                               }
                                            }
                                });
                            }
                        }

                });
            }
        });

        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    /*public void onClick(View v){
        if(v.getId()==R.id.create)
        {
            registerInfo();
        }
    }*/

    /* public void registerInfo(View view) {
        String email = register_email.getText().toString().trim();
        String username = register_user.getText().toString().trim();
        String password = register_password.getText().toString().trim();

        if(email.isEmpty())
        {
            register_email.setError("Email is required!");
            register_email.requestFocus();
            return;
        }
        if(username.isEmpty())
        {
            register_user.setError("Username is required");
            register_user.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            register_password.setError("Password is required");
            register_user.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            register_email.setError("Email is required!");
            register_email.requestFocus();
            return;
        }
        if(password.length() < 6)
        {
            register_password.setError("Password should be at least 6 characters");
            register_password.requestFocus();
            return;
        }
    } */

    //-----------------------------------------




    /*public void BtnClicked(View view){
        TextView txtWelcome = findViewById(R.id.txtwelcome);
        txtWelcome.setText("Hello Raad");
    }*/
}
