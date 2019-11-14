package com.example.smasheditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText editTextPseudo;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextConfirmPassword;
    Button buttonSignIn;
    TextView textViewGoConnecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        editTextPseudo = (EditText) findViewById(R.id.editTextPseudo);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        textViewGoConnecter = (TextView) findViewById(R.id.textViewGoInscrire);

        textViewGoConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogIn();
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = checkNonEmpty(editTextEmail);
                ok = ok && checkNonEmpty(editTextPseudo);
                ok = ok && checkNonEmpty(editTextPassword);
                ok = ok && checkNonEmpty(editTextConfirmPassword);
                ok = ok && (editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString()));

                if(ok) {
                    créerCompte(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                }
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

    private void goToLogIn() {
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
    }

    private void créerCompte(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUser(user);
                }else {
                    Toast.makeText(SignInActivity.this, "Echec de l'Authentification.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUser(FirebaseUser user) {
        Intent myIntent;
        if(user != null) {

            //ajouter récupération des données de l'utilisateur

            myIntent = new Intent(this, MainMenuActivity.class);
        }else {
            myIntent = new Intent(this, LoginActivity.class);
        }
        startActivity(myIntent);
    }


    boolean checkNonEmpty(EditText editText) {
        if(editText.getText().toString().equals("")){
            editText.setError("champ vide");
            return false;
        }
        return true;
    }

}
