package com.kawesi.sugarcakes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.kawesi.sugarcakes.data.CakeCategory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateCakeActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.kawei.sugarcakes.REPLY";
    public static final String IMAGE_VIEW_NAME = "imageViewName";
    private EditText cakeName, cakePrice, cakeIngredients;
    private ImageView cakeImageView, otherImage1View, otherImage2View, otherImage3View;
    private String name, price, ingredients;
    private Bitmap mainImage, otherCakeImage1, otherCakeImage2, otherCakeImage3;
    private int categoryKey;



    private Spinner categorySpinner;

    public static final int GALLERY_REQUEST_CODE = 200;
    private static final int REQUEST_IMAGE_CAPTURE = 100;
    private String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cake);

        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        String[] items = CakeCategory.categoryMap.values().toArray(new String[]{});

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        categorySpinner.setAdapter(adapter);

        cakeImageView = (ImageView) findViewById(R.id.createcake_imageView);
        otherImage1View = (ImageView) findViewById(R.id.otherPicture1_imageView);
        otherImage2View = (ImageView) findViewById(R.id.otherPicture2_imageView);
        otherImage3View = (ImageView) findViewById(R.id.otherPicture3_imageView);

        cakeName = findViewById(R.id.cakeNameEditText);
        cakePrice = findViewById(R.id.price_editText);
        final Date currentTime = Calendar.getInstance().getTime();
        cakeIngredients = findViewById(R.id.ingredients_editText);


        cakeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();    // passin the image view

            }
        });

        final Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
//                if (TextUtils.isEmpty(cakeName.getText())){
//
//                } else {
//
//                }
                // create a newcake and pass it as a bundle
                Cake cake = new Cake(
                        cakeName.getText().toString(),
                        Integer.parseInt(cakePrice.getText().toString()),
                        currentTime,
                        cakeIngredients.getText().toString(),
                        Integer.parseInt(categorySpinner.getSelectedItem().toString())
                );
                replyIntent.putExtra(EXTRA_REPLY, cake);

                setResult(RESULT_OK, replyIntent);
                finish();

            }
        });
        final Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent();
                setResult(RESULT_CANCELED, cancelIntent);
                finish();
            }
        });

    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog =  new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] picDialogItems = {"Select Photo From Gallery", "Capture Photo from Camera"};
        pictureDialog.setItems(picDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        dispatchSelectPictureIntent();
                        break;
                    case 1:
                        distaptchCameraIntent();
                        break;
                }
            }
        });
        pictureDialog.show();
    }

    private void dispatchSelectPictureIntent() {
        Intent selectOrTakePhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
        selectOrTakePhotoIntent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/jpg", "image/png"};
        selectOrTakePhotoIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(selectOrTakePhotoIntent, GALLERY_REQUEST_CODE);

    }

    private void distaptchCameraIntent() {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePhotoIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e){
                e.printStackTrace();
            }
            if (photoFile != null){
                Uri photoUri = FileProvider.getUriForFile(this,
                        "com.kawesi.sugarcakes.fileprovider", photoFile);


                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE);
            }


        }
    }

    private File createImageFile() throws IOException {
        String timeSTamp = new SimpleDateFormat("yyyyMMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeSTamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE){
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            cakeImageView.setImageBitmap(bitmap);
        }

        if (requestCode == GALLERY_REQUEST_CODE){
            if (data != null){
                Uri contetUri = data.getData();
                try{
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contetUri);
                    cakeImageView.setImageBitmap(bitmap);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    }




}
