package com.ensibuuko.ambrose.gocheckout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PaymentSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
