package com.ensibuuko.ambrose.gocheckout.Checkouts;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class BarcodeItemCheckout extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    private static final String TAG = "BarcodeItem";
    private static final int MY_REQUEST_CODE = 1994;
    private ZBarScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
            } else {
                // Your app will not have this permission. Turn off all functions
                // that require this permission or it will force close like your
                // original question
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v(TAG, rawResult.getContents());

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        TextView title = new TextView(this);
        title.setText("Add to Cart");
        title.setPadding(100, 50, 100, 50);
        title.setTextColor(Color.BLACK);
        title.setTextSize(18);
        alertDialog.setCustomTitle(title);

        // message
        TextView msg = new TextView(this);
        msg.setText("Item Code '"+rawResult.getContents()+"' will be added to your cart. Press add to proceed or cancel.");
        msg.setPadding(100, 50, 100, 50);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // send to backend cart for  cart activity to pull and for checkout.
                startActivity(new Intent(BarcodeItemCheckout.this, Cart.class));
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.cancel();
            }
        });

        new Dialog(getApplicationContext());
        alertDialog.show();

        Log.v(TAG, rawResult.getBarcodeFormat().getName());
        mScannerView.resumeCameraPreview(this);
    }
}
