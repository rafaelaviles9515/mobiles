package com.example.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registrar extends AppCompatActivity {

    //private EditText nombre;
    private String nombreb;
    private String emailb;
    private String contraseñab;

    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registrar);
        mAuth = FirebaseAuth.getInstance();
        //nombre=(EditText) findViewById(R.id.nombre);
        final EditText nombre = findViewById(R.id.nombre);
        final EditText email = findViewById(R.id.email);
        final EditText contraseña = findViewById(R.id.contraseña);

        final Button loginButton = findViewById(R.id.btnRegistrar);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreb= nombre.getText().toString();
                emailb= email.getText().toString();
                contraseñab= contraseña.getText().toString();

                registerUser();

            }
        });

    }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(emailb,contraseñab).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(Registrar.this, LoginActivity.class));
                    Toast.makeText(Registrar.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Registrar.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
