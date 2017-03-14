package com.rybalko.jakub.myapplicationa;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button aparat;
    final static private int CAPTURE_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aparat = (Button) findViewById(R.id.button);

        aparat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File mediaDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ProjektSzkola");
                File imgFile = new File(mediaDir.getPath() + File.separator + "IMG_" + "ProjektSzkola" + ".jpg");
                Uri photoURI = FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID + ".provider", getOutputMediaFile());
                Uri imgUri = Uri.fromFile(new File(imgFile.getAbsolutePath()));
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= 24) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, CAPTURE_IMAGE);
                }else{
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                    startActivityForResult(intent, CAPTURE_IMAGE);
                }
            }
        });

    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ProjektSzkola");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("ProjektSzkola", "problem z utworzeniem katalogu");
                return null;
            }
        }

        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + "ProjektSzkola" + ".jpg");
        if (mediaFile.exists()) {
            mediaFile.delete();
        }
        Log.d("Zdjecie1234567 : ", mediaFile.toString());

        return mediaFile;
    }


    }

