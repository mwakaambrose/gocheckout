package com.ensibuuko.ambrose.gocheckout.Controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ensibuuko.ambrose.gocheckout.Models.Cart;
import com.ensibuuko.ambrose.gocheckout.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartController extends ArrayAdapter<Cart> {

    private Context context;
    private List<Cart> items = new ArrayList<>();

    public CartController(Context context, List<Cart> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(this.context).inflate(R.layout.cart_item_row, parent,false);

        Cart cart = items.get(position);

        ImageView image = listItem.findViewById(R.id.item_icon);
        Picasso.get().load(cart.getItemImageUrl()).into(image);

        TextView item_title = listItem.findViewById(R.id.item_title);
        item_title.setText(cart.getItemName());

        TextView item_description = listItem.findViewById(R.id.item_description);
        item_description.setText(cart.getItemDescription());
        return listItem;
    }
}
