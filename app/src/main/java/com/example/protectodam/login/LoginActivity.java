package com.example.protectodam.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.protectodam.MainActivity;
import com.example.protectodam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import maes.tech.intentanim.CustomIntent;

public class LoginActivity extends AppCompatActivity {

    private EditText et_correoLogin;
    private EditText et_passwordLogin;
    private EditText et_correoSignin;
    private EditText et_passwordSignin;
    private Button bt_login;
    private Button bt_signin;

    private String correoLogin;
    private String passwordLogin;
    private String correoSignin;
    private String passwordSignin;

    private FirebaseAuth firebaseAuth;

    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_correoLogin = findViewById(R.id.et_emailLogin);
        et_passwordLogin = findViewById(R.id.et_passwordLogin);
        bt_login = findViewById(R.id.bt_login);

        et_correoSignin = findViewById(R.id.et_emailSignin);
        et_passwordSignin = findViewById(R.id.et_passwordSignin);
        bt_signin = findViewById(R.id.bt_Signin);

        firebaseAuth = FirebaseAuth.getInstance();

        bt_login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    bt_login.setBackground(getDrawable(R.color.rojoJuntaCyL));
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    bt_login.setBackground(getDrawable(R.color.rojoJuntaCyL2));
                }
                return false;
            }
        });

        bt_signin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    bt_signin.setBackground(getDrawable(R.color.rojoJuntaCyL));
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    bt_signin.setBackground(getDrawable(R.color.rojoJuntaCyL2));
                }
                return false;
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correoLogin = et_correoLogin.getText().toString();
                passwordLogin = et_passwordLogin.getText().toString();

                if (!correoLogin.isEmpty() && !passwordLogin.isEmpty()) {
                    loginUsuario();
                } else {
                    if (correoLogin.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Introduce el correo", Toast.LENGTH_SHORT).show();
                    } else if (passwordLogin.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Introduce la contraseña", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Introduce el correo y la contraseña", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });

        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correoSignin = et_correoSignin.getText().toString();
                passwordSignin = et_passwordSignin.getText().toString();


                if (!correoSignin.isEmpty() && !passwordSignin.isEmpty()) {
                    signinUsuario();
                } else {
                    if (correoSignin.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Introduce el correo", Toast.LENGTH_SHORT).show();
                    } else if (passwordSignin.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Introduce la contraseña", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Introduce el correo y la contraseña", Toast.LENGTH_SHORT).show();
                    }

                }


            }

        });


    }

    private void loginUsuario() {
        firebaseAuth.signInWithEmailAndPassword(correoLogin, passwordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    CustomIntent.customType(LoginActivity.this, "left-to-rigth");//animacion de transicion
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Correo o contreaseña incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void signinUsuario(){
        firebaseAuth.createUserWithEmailAndPassword(correoSignin, passwordSignin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        //CustomIntent.customType(LoginActivity.this, "left-to-rigth");//animacion de transicion
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error al registrar nuevo usuario", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //hacemos que la aplicacion no haga nada al pulsar el boton de retroceso
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }
}