package com.pc.cgodemo;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pc.cgodemo.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private static final int RC_CAMERA_AND_LOCATION = 0x10;
    private ActivityMainBinding binding;

    private final static String SDP= "eyJ0eXBlIjoib2ZmZXIiLCJzZHAiOiJ2PTBcclxubz0tIDExODgzNjk4Mzc4NjU1NjM5MTAgMiBJTiBJUDQgMTI3LjAuMC4xXHJcbnM9LVxyXG50PTAgMFxyXG5hPWdyb3VwOkJVTkRMRSAwXHJcbmE9ZXh0bWFwLWFsbG93LW1peGVkXHJcbmE9bXNpZC1zZW1hbnRpYzogV01TXHJcbm09YXBwbGljYXRpb24gNjM4ODIgVURQL0RUTFMvU0NUUCB3ZWJydGMtZGF0YWNoYW5uZWxcclxuYz1JTiBJUDQgMTkyLjE2OC4yMjAuMVxyXG5hPWNhbmRpZGF0ZTozNDA1MzM5ODcgMSB1ZHAgMjEyMjI2MDIyMyAxOTIuMTY4LjIyMC4xIDYzODgyIHR5cCBob3N0IGdlbmVyYXRpb24gMCBuZXR3b3JrLWlkIDJcclxuYT1jYW5kaWRhdGU6MjM5Nzk3MTc5MiAxIHVkcCAyMTIyMTk0Njg3IDE5Mi4xNjguNi4xIDYzODgzIHR5cCBob3N0IGdlbmVyYXRpb24gMCBuZXR3b3JrLWlkIDNcclxuYT1jYW5kaWRhdGU6MzM5OTQ4NTU5MyAxIHVkcCAyMTIyMTI5MTUxIDE3Mi4xNi41MC4xODQgNjM4ODQgdHlwIGhvc3QgZ2VuZXJhdGlvbiAwIG5ldHdvcmstaWQgMSBuZXR3b3JrLWNvc3QgMTBcclxuYT1jYW5kaWRhdGU6MTc4NzAxOTM4NyAxIHRjcCAxNTE4MjgwNDQ3IDE5Mi4xNjguMjIwLjEgOSB0eXAgaG9zdCB0Y3B0eXBlIGFjdGl2ZSBnZW5lcmF0aW9uIDAgbmV0d29yay1pZCAyXHJcbmE9Y2FuZGlkYXRlOjQwMjg3NDU2NzIgMSB0Y3AgMTUxODIxNDkxMSAxOTIuMTY4LjYuMSA5IHR5cCBob3N0IHRjcHR5cGUgYWN0aXZlIGdlbmVyYXRpb24gMCBuZXR3b3JrLWlkIDNcclxuYT1jYW5kaWRhdGU6MzAyNzIzMzI4MSAxIHRjcCAxNTE4MTQ5Mzc1IDE3Mi4xNi41MC4xODQgOSB0eXAgaG9zdCB0Y3B0eXBlIGFjdGl2ZSBnZW5lcmF0aW9uIDAgbmV0d29yay1pZCAxIG5ldHdvcmstY29zdCAxMFxyXG5hPWljZS11ZnJhZzpVZXI2XHJcbmE9aWNlLXB3ZDpXWEU0dTJjemxRd09TT1J4bmhXYjI4ZWlcclxuYT1pY2Utb3B0aW9uczp0cmlja2xlXHJcbmE9ZmluZ2VycHJpbnQ6c2hhLTI1NiA1Mjo1OToyMDpCRTpDNzo1NDo5QjowMDpCNjoxRDo4OToxQjo3ODpGNzo4ODpERTpBNzpBNzpENDpGNDo4NDo1NTpDQjozNDoxODo2QTo0Mzo0Njo1MTo4NjpGRDowOVxyXG5hPXNldHVwOmFjdHBhc3NcclxuYT1taWQ6MFxyXG5hPXNjdHAtcG9ydDo1MDAwXHJcbmE9bWF4LW1lc3NhZ2Utc2l6ZToyNjIxNDRcclxuIn0=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        methodRequiresPermissions();

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText("add " + 1);

        binding.sampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File log = new File(Environment.getExternalStorageDirectory() + File.separator + "rtc.log");
                JniLibrary.TestCustomLogger(log.getAbsolutePath());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    private void methodRequiresPermissions() {
        String[] perms = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.CHANGE_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.title_settings_dialog),
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

}