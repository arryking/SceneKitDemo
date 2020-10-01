package com.huawei.ktas.scenekitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.huawei.hms.scene.sdk.ARView;

public class OfficeActivity extends Activity {
    private ARView mARView;
    private Button mButton;
    private boolean isLoadResource = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);
        mARView = findViewById(R.id.ar_view);
        mButton = findViewById(R.id.button);

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


    public void onButtonToggleClicked(View view) {
        mARView.enablePlaneDisplay(true);

        if (!isLoadResource) {
            // Load 3D model.
            mARView.loadAsset("ARView/scene.gltf");
            float[] scale = new float[] { 0.01f, 0.01f, 0.01f };
            float[] rotation = new float[] { 0.707f, 0.0f, -0.707f, 0.0f };
            // (Optional) Set the initial status.
            mARView.setInitialPose(scale, rotation);
            isLoadResource = true;
            mButton.setText("Clear Object");
        } else {
            // Clear the resources loaded in the ARView.
            mARView.clearResource();
            mARView.loadAsset("");
            isLoadResource = false;
            mButton.setText("Load Object");
        }
    }
}