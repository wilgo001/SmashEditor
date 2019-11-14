package com.example.smasheditor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    TextView textViewGoInscrire;
    Button buttonLogIn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewGoInscrire = (TextView) findViewById(R.id.textViewGoInscrire);
        buttonLogIn = (Button) findViewById(R.id.buttonLogin);
        mAuth = FirebaseAuth.getInstance();

        textViewGoInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignIn();
            }
        });

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ok = checkNonEmpty(editTextEmail);
                ok = ok && checkNonEmpty(editTextPassword);
                if(ok)
                    LogIn(editTextEmail.getText().toString(), editTextPassword.getText().toString());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUser(currentUser);
    }

    private void LogIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUser(user);
                }else{
                    Toast.makeText(LoginActivity.this, "Echec de connexion.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean checkNonEmpty(EditText editText) {
        if(editText.getText().toString().equals("")){
            editText.setError("champ vide");
            return false;
        }
        return true;
    }

    private void goToSignIn() {
        Intent myIntent = new Intent(this, SignInActivity.class);
        startActivity(myIntent);
    }

    private void updateUser(FirebaseUser user) {
        if(user != null) {

            //ajouter récupération des données de l'utilisateur

            Intent myIntent = new Intent(this, MainMenuActivity.class);
            startActivity(myIntent);
        }
    }
}
