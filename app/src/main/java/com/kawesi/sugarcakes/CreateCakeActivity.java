package com.kawesi.sugarcakes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
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
import android.widget.Toast;

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
    private static final String[] PERMISSION_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    } ;
    private static final int CODE_WRITE_STORAGE_PERMISSION = 300;
    private EditText cakeName, cakePrice, cakeIngredients;
    private ImageView cakeImageView, otherImage1View, otherImage2View, otherImage3View;
    private String name, price, ingredients;
    private Bitmap mainImage, otherCakeImage1, otherCakeImage2, otherCakeImage3;
    private int categoryKey;



    private Spinner categorySpinner;
    // we have four sets of images, that will mean four codes of gallery request and four of camera request
    public static final int MAIN_IMG_CAMERA_REQUEST_CODE = 100;
    public static final int MAIN_IMG_GALLERY_REQUEST_CODE = 200;

    public static final int OTHER_IMG_ONE_CAMERA_REQUEST_CODE = 101;
    public static final int OTHER_IMG_ONE_GALLERY_REQUEST_CODE = 201;

    public static final int OTHER_IMG_TWO_CAMERA_REQUEST_CODE = 102;
    public static final int OTHER_IMG_TWO_GALLERY_REQUEST_CODE = 202;

    public static final int OTHER_IMG_THREE_CAMERA_REQUEST_CODE = 103;
    public static final int OTHER_IMG_THREE_GALLERY_REQUEST_CODE = 203;

    // Path and uri for the four images
    private String currentCameraPathMainImg;
    private Uri mainImageGlobalUri;

    private String currentCameraPathOtherImgOne;
    private Uri otherImageOneGlobalUri;

    private String currentCameraPathOtherImgTwo;
    private Uri otherImageTwoGlobalUri;

    private String currentCameraPathOtherImgThree;
    private Uri otherImageThreeGlobalUri;



    public static final int GALLERY_REQUEST_CODE = 2000;
    private static final int REQUEST_IMAGE_CAPTURE = 1000;
    private String currentPhotoPath;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cake);

        mContext = this;

        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        String[] items = CakeCategory.categoryMap.values().toArray(new String[]{});

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        categorySpinner.setAdapter(adapter);

        cakeImageView = (ImageView) findViewById(R.id.createcake_imageView);
        cakeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mainImage = "mainImage";
                selectOrTakeImage(mContext, MAIN_IMG_GALLERY_REQUEST_CODE, MAIN_IMG_CAMERA_REQUEST_CODE, mainImage);

            }
        });
        otherImage1View = (ImageView) findViewById(R.id.otherPicture1_imageView);
        otherImage1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otherImageOne = "otherImageOne";
                selectOrTakeImage(mContext, OTHER_IMG_ONE_GALLERY_REQUEST_CODE, OTHER_IMG_ONE_CAMERA_REQUEST_CODE, otherImageOne);
            }
        });
        otherImage2View = (ImageView) findViewById(R.id.otherPicture2_imageView);
        otherImage2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otherImageTwo = "otherImageTwo";
                selectOrTakeImage(mContext, OTHER_IMG_TWO_GALLERY_REQUEST_CODE, OTHER_IMG_TWO_CAMERA_REQUEST_CODE, otherImageTwo);

            }
        });
        otherImage3View = (ImageView) findViewById(R.id.otherPicture3_imageView);
        otherImage3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otherImageThree = "otherImageThree";
                selectOrTakeImage(mContext, OTHER_IMG_THREE_GALLERY_REQUEST_CODE, OTHER_IMG_THREE_CAMERA_REQUEST_CODE, otherImageThree);
            }
        });

        cakeName = findViewById(R.id.cakeNameEditText);
        cakePrice = findViewById(R.id.price_editText);
        final Date currentTime = Calendar.getInstance().getTime();
        cakeIngredients = findViewById(R.id.ingredients_editText);


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
                        (int)categorySpinner.getSelectedItemId()
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

    private void selectOrTakeImage(final Context context, final int galleryRequestCode, final int cameraRequestCode, final String imageNumber ){
        final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose/Take Cake Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals("Take Photo")){
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (cameraIntent.resolveActivity(getPackageManager()) != null){
                        File imageFile = null;
                        try {

                            if (imageNumber.equals("mainImage")){
                                imageFile = createImageFile("mainImage");

                            }
                            if (imageNumber.equals("otherImageOne")){
                                imageFile = createImageFile("otherImageOne");

                            }
                            if (imageNumber.equals("otherImageTwo")){
                                imageFile = createImageFile("otherImageTwo");

                            }
                            if (imageNumber.equals("otherImageThree")){
                                imageFile = createImageFile("otherImageThree");

                            }

                        } catch (Exception ex){
                            ex.printStackTrace();
                        }

                        if (imageFile != null){
                            Uri photoUri = FileProvider.getUriForFile(context, "com.kawesi.sugarcakes.fileprovider", imageFile);

                            if (imageNumber.equals("mainImage")){
                                mainImageGlobalUri = photoUri;

                            }else
                            if (imageNumber.equals("otherImageOne")){
                                otherImageOneGlobalUri = photoUri;

                            } else
                            if (imageNumber.equals("otherImageTwo")){
                                otherImageTwoGlobalUri = photoUri;

                            } else
                            if (imageNumber.equals("otherImageThree")){
                                otherImageThreeGlobalUri = photoUri;

                            }

                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                            startActivityForResult(cameraIntent, cameraRequestCode);
                        }
                    }
                }else
                if (options[which].equals("Choose From Gallery")){
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, galleryRequestCode);


                } else
                if (options[which].equals("Cancel")){
                    dialog.dismiss();
                }
            }
        });

        builder.create().show();
    }






    private File createImageFile(String imageNo) throws IOException {
        String timeSTamp = new SimpleDateFormat("yyyyMMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeSTamp + "_";
        // check for permission
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) == PackageManager.PERMISSION_GRANTED){
            File storageDirExt = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDirExt
            );
            if (imageNo.equals("mainImage")){
               currentCameraPathMainImg = image.getAbsolutePath();

            }else
            if (imageNo.equals("otherImageOne")){
                currentCameraPathOtherImgOne = image.getAbsolutePath();

            } else
            if (imageNo.equals("otherImageTwo")){
                currentCameraPathOtherImgTwo = image.getAbsolutePath();

            } else
            if (imageNo.equals("otherImageThree")){
                currentCameraPathOtherImgThree = image.getAbsolutePath();
            }

            return  image;
        } else {
            requestPermissions();
            return null;
        }

    }

    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permission Needed");
            builder.setMessage("This permission is needed to be able to save images to the Phone gallery");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(CreateCakeActivity.this,
                            PERMISSION_STORAGE, CODE_WRITE_STORAGE_PERMISSION);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.create().show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSION_STORAGE, CODE_WRITE_STORAGE_PERMISSION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case MAIN_IMG_CAMERA_REQUEST_CODE:
                    cakeImageView.setImageURI(mainImageGlobalUri);
                    galleryAddPic(currentCameraPathMainImg);
                    break;
                case MAIN_IMG_GALLERY_REQUEST_CODE:
                    Uri selectedImg = data.getData();
                    cakeImageView.setImageURI(selectedImg);
                    break;
                case OTHER_IMG_ONE_CAMERA_REQUEST_CODE:
                    otherImage1View.setImageURI(otherImageOneGlobalUri);
                    galleryAddPic(currentCameraPathOtherImgOne);
                    break;
                case OTHER_IMG_ONE_GALLERY_REQUEST_CODE:
                    Uri selectedImg1 = data.getData();
                    otherImage1View.setImageURI(selectedImg1);
                    break;
                case OTHER_IMG_TWO_CAMERA_REQUEST_CODE:
                    otherImage2View.setImageURI(otherImageTwoGlobalUri);
                    galleryAddPic(currentCameraPathOtherImgTwo);
                    break;
                case OTHER_IMG_TWO_GALLERY_REQUEST_CODE:
                    Uri selectedImg2 = data.getData();
                    otherImage2View.setImageURI(selectedImg2);
                    break;
                case OTHER_IMG_THREE_CAMERA_REQUEST_CODE:
                    otherImage1View.setImageURI(otherImageThreeGlobalUri);
                    galleryAddPic(currentCameraPathOtherImgThree);
                    break;
                case OTHER_IMG_THREE_GALLERY_REQUEST_CODE:
                    Uri selectedImg3 = data.getData();
                    otherImage3View.setImageURI(selectedImg3);
                    break;
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CODE_WRITE_STORAGE_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied, you wont be able to save pictures", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void galleryAddPic(String currentPath){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

}
