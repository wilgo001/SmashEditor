package com.example.smasheditor;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenuActivity extends AppCompatActivity {

    private Button buttonCreate, buttonEdit, buttonDeco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCreate = (Button) findViewById(R.id.createButton);
        buttonEdit = (Button) findViewById(R.id.buttonEdit);
        buttonDeco = (Button) findViewById(R.id.buttonDeco);

        buttonDeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                quitApp();
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard();
            }
        });


    }

    private void createCard() {
        Intent myIntent = new Intent(this, CreateCardActivity.class);
        startActivity(myIntent);
    }


    private void quitApp() {
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
    }
}
