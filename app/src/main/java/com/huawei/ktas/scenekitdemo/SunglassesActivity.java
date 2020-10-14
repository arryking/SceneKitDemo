package com.huawei.ktas.scenekitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.huawei.hms.scene.sdk.FaceView;
import com.huawei.hms.scene.sdk.common.LandmarkType;

public class SunglassesActivity extends Activity {

    private FaceView mFaceView;
    private boolean isLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunglasses);
        mFaceView = findViewById(R.id.face_view);
        init();
    }

    private void init() {
        final float[] position = {0.0f, 0.032f, 0.0f};
        final float[] rotation = {1.0f, -0.1f, 0.0f, 0.0f};
        final float[] scale = {0.0004f, 0.0004f, 0.0004f};

        mFaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLoaded) {
                    // Load materials.
                    int index = mFaceView.loadAsset("FaceView/sunglasses_mustang.glb", LandmarkType.TIP_OF_NOSE);
                    // (Optional) Set the initial status.
                    if(index < 0){
                        Toast.makeText(SunglassesActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                    }
                    mFaceView.setInitialPose(index, position, scale, rotation);
                    isLoaded = true;
                }
                else{
                    mFaceView.clearResource();
                    mFaceView.loadAsset("", LandmarkType.TIP_OF_NOSE);
                    isLoaded = false;
                }
            }
        });
    }

    /**
     * Synchronously call the onResume() method of the FaceView.
     */
    @Override
    protected void onResume() {
        super.onResume();
        mFaceView.onResume();
    }

    /**
     * Synchronously call the onPause() method of the FaceView.
     */
    @Override
    protected void onPause() {
        super.onPause();
        mFaceView.onPause();
    }

    /**
     * If quick rebuilding is allowed for the current activity, destroy() of FaceView must be invoked synchronously.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFaceView.destroy();
    }
}