package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.facebook.login.LoginManager;
import com.facebook.AccessToken;

public class inicio extends AppCompatActivity {

    Button btn_cerrars;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        auth = FirebaseAuth.getInstance();

        btn_cerrars = findViewById(R.id.btn_cerrarS);
        btn_cerrars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesionFacebook();
                cerrarSesionGoogle();
            }
        });

    }



    private void irLogin() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    // Función para cerrar sesión en Facebook
    private void cerrarSesionFacebook() {
        // Verifica si hay una sesión activa de Facebook
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if (accessToken != null && !accessToken.isExpired()) {
            // Cierra la sesión de Facebook utilizando el LoginManager
            LoginManager.getInstance().logOut();
            irLogin();
            finish();
        }
    }

    // Función para cerrar sesión en Google
    private void cerrarSesionGoogle() {
          auth.signOut();
            irLogin();
        }
    }