package com.ensibuuko.ambrose.gocheckout.Payments;

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
import android.widget.TextView;

import com.ensibuuko.ambrose.gocheckout.Checkouts.BarcodeItemCheckout;
import com.ensibuuko.ambrose.gocheckout.Checkouts.Cart;
import com.ensibuuko.ambrose.gocheckout.PaymentSuccess;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodePay extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final String TAG = "QRCodePay";
    private static final int MY_REQUEST_CODE = 1993;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);  // Set the scanner view as the content view
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
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        TextView title = new TextView(this);
        title.setText("Checkout?");
        title.setPadding(100, 50, 100, 50);
        title.setTextColor(Color.BLACK);
        title.setTextSize(18);
        alertDialog.setCustomTitle(title);

        // message
        TextView msg = new TextView(this);
        msg.setText("Payment of amount '"+rawResult.getText()+"' will be deducted from your mobile money account. Press Ok to proceed or cancel if you are not sure.");
        msg.setPadding(100, 50, 100, 50);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // make payment when succeeded, show succeeded activity or show failed activity
                startActivity(new Intent(QRCodePay.this, PaymentSuccess.class));
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.cancel();
            }
        });

        new Dialog(getApplicationContext());
        alertDialog.show();

        mScannerView.resumeCameraPreview(this);
    }
}
