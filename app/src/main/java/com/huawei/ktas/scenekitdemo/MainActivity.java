package com.huawei.ktas.scenekitdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int FACE_VIEW_REQUEST_CODE = 5;
    private static final int AR_VIEW_REQUEST_CODE = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case FACE_VIEW_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(this, SunglassesActivity.class));
                }
                break;
            case AR_VIEW_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(this, OfficeActivity.class));
                }
                break;
            default:
                break;
        }
    }

    public void onOfficeClicked(View v){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{ Manifest.permission.CAMERA }, AR_VIEW_REQUEST_CODE);
        } else {
            startActivity(new Intent(this, OfficeActivity.class));
        }
    }

    public void onSunglassesClicked(View v){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{ Manifest.permission.CAMERA }, FACE_VIEW_REQUEST_CODE);
        } else {
            startActivity(new Intent(this, SunglassesActivity.class));
        }
    }

    public void onShoesClicked(View v){
        startActivity(new Intent(this, ShoesActivity.class));
    }
}