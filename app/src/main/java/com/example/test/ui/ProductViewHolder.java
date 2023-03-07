package com.example.test.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;

public class ProductViewHolder {
    int status;
    ImageView productImage;
    TextView productTitle;
    TextView productDetails;

    ProductViewHolder(View v) {
        status = 0;
        productImage = v.findViewById(R.id.imageView);
        productTitle = v.findViewById(R.id.TextView1);
        productDetails = v.findViewById(R.id.TextView2);
    }
}
