package com.ensibuuko.ambrose.gocheckout.Checkouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ensibuuko.ambrose.gocheckout.Controllers.CartController;
import com.ensibuuko.ambrose.gocheckout.Payments.QRCodePay;
import com.ensibuuko.ambrose.gocheckout.R;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    private Button pay;
    private ListView listView;
    private CartController cartController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = findViewById(R.id.cart_items);
        ArrayList<com.ensibuuko.ambrose.gocheckout.Models.Cart> CartsList = new ArrayList<>();
        CartsList.add(new com.ensibuuko.ambrose.gocheckout.Models.Cart("Dove", "Mens body spray" , "https://images-na.ssl-images-amazon.com/images/I/81XDajOi5gL._SY355_.jpg"));
        CartsList.add(new com.ensibuuko.ambrose.gocheckout.Models.Cart("Bread", "Big country bakery bread" , "https://www.dessertfortwo.com/wp-content/uploads/2011/10/BananaBreadFinalforsite.jpg"));
        CartsList.add(new com.ensibuuko.ambrose.gocheckout.Models.Cart("Olive oil hair", "Dundrupt care hair spray" , "https://images-na.ssl-images-amazon.com/images/I/91v-MNKSyOL._SY450_.jpg"));
        CartsList.add(new com.ensibuuko.ambrose.gocheckout.Models.Cart("Nivea", "Nivea mens body cream" , "https://images-na.ssl-images-amazon.com/images/I/819w6IwVJ5L._SY355_.jpg"));

        cartController = new CartController(this, CartsList);
        listView.setAdapter(cartController);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Cart.this, "Item: "+CartsList.get(position).getItemName(), Toast.LENGTH_SHORT).show();
            }
        });

        pay = findViewById(R.id.pay);
        pay.setOnClickListener(view -> startActivity(new Intent(this, QRCodePay.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_to_cart:
                startActivity(new Intent(this, BarcodeItemCheckout.class));
                break;
            case R.id.empty_cart:
                Toast.makeText(this, "Emptying Cart", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
