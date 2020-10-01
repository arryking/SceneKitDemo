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
        final float[] position = {0.0f, 0.0f, 0.0f};
        final float[] rotation = {1.0f, 0.0f, 0.0f, 0.0f};
        final float[] scale = {1.0f, 1.0f, 1.0f};

        mFaceView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(!isLoaded) {
                    // Load materials.
                    int index = mFaceView.loadAsset("FaceView/scene.gltf", LandmarkType.TIP_OF_NOSE);
                    // (Optional) Set the initial status.
                    if(index < 0){
                        Toast.makeText(SunglassesActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                        return true;
                    }
                    mFaceView.setInitialPose(index, position, rotation, scale);
                    isLoaded = true;
                }
                else{
                    mFaceView.clearResource();
                    mFaceView.loadAsset("", LandmarkType.TIP_OF_NOSE);
                    isLoaded = false;
                }
                return true;
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