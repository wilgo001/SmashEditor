package com.example.smasheditor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class CreateCardActivity extends AppCompatActivity {

    private static final int GALLERY = 0;
    public static final int CAMERA = 1;
    Spinner cardType;
    Button choosePicture;
    EditText nom, description;
    LinearLayout linearLayout;
    String[] typeList;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        cardType = (Spinner) findViewById(R.id.cardType);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        choosePicture = (Button) findViewById(R.id.selectImage);
        nom = (EditText) findViewById(R.id.cardName);
        description = (EditText) findViewById(R.id.description);
        imageView = (ImageView) findViewById(R.id.imageView);
        typeList = getResources().getStringArray(R.array.typeCard);

        cardType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deleteTooManyEdit();
                if (parent.getItemAtPosition(position).toString().equals("smasheur"))
                    addAttAndDef();
                else if (parent.getItemAtPosition(position).toString().equals("smasheur")) {
                    addAttAndDef();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        choosePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(CreateCardActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(CreateCardActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(CreateCardActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    private String saveImage(Bitmap thumbnail) {
        return "";
    }


    private void deleteTooManyEdit() {

    }

    private void addAttAndDef() {
        EditText attack = new EditText(this);
        attack.setHint("val Attaque");
        EditText defense = new EditText(this);
        defense.setHint("val Defense");
        linearLayout.addView(attack);
        linearLayout.addView(defense);
    }
}
