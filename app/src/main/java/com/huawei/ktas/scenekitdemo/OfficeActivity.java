package com.huawei.ktas.scenekitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.huawei.hms.scene.sdk.ARView;

public class OfficeActivity extends Activity {
    private ARView mARView;
    private Button mButtonFlower;
    private boolean isLoadFlowerResource = false;
    private boolean isLoadAquariumResource = false;
    private Button mButtonAquarium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);
        mARView = findViewById(R.id.ar_view);
        mButtonFlower = findViewById(R.id.button_flower);
        mButtonAquarium = findViewById(R.id.button_aquarium);

        Toast.makeText(this, "Please move the mobile phone slowly to find the plane", Toast.LENGTH_LONG).show();
    }

    /**
     * Synchronously call the onPause() method of the ARView.
     */
    @Override
    protected void onPause() {
        super.onPause();
        mARView.onPause();
    }

    /**
     * Synchronously call the onResume() method of the ARView.
     */
    @Override
    protected void onResume() {
        super.onResume();
        mARView.onResume();
    }

    /**
     * If quick rebuilding is allowed for the current activity, destroy() of ARView must be invoked synchronously.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mARView.destroy();
    }


    public void onButtonFlowerToggleClicked(View view) {
        mARView.enablePlaneDisplay(true);

        if (!isLoadFlowerResource) {
            // Load 3D model.
            mARView.loadAsset("ARView/flower.glb");
            float[] scale = new float[] { 0.15f, 0.15f, 0.15f };
            float[] rotation = new float[] { 0.707f, 0.0f, -0.500f, 0.0f };
            // (Optional) Set the initial status.
            mARView.setInitialPose(scale, rotation);
            isLoadFlowerResource = true;
            mButtonFlower.setText("Clear Flower");
        } else {
            // Clear the resources loaded in the ARView.
            mARView.clearResource();
            mARView.loadAsset("");
            isLoadFlowerResource = false;
            mButtonFlower.setText("Load Flower");
        }
    }

    public void onButtonAquariumToggleClicked(View view) {
        mARView.enablePlaneDisplay(true);

        if (!isLoadAquariumResource) {
            // Load 3D model.
            mARView.loadAsset("ARView/aquarium.glb");
            float[] scale = new float[] { 0.015f, 0.015f, 0.015f };
            float[] rotation = new float[] { 0.0f, 0.0f, 0.0f, 0.0f };
            // (Optional) Set the initial status.
            mARView.setInitialPose(scale, rotation);
            isLoadAquariumResource = true;
            mButtonAquarium.setText("Clear Aquarium");
        } else {
            // Clear the resources loaded in the ARView.
            mARView.clearResource();
            mARView.loadAsset("");
            isLoadAquariumResource = false;
            mButtonAquarium.setText("Load Aquarium");
        }
    }
}