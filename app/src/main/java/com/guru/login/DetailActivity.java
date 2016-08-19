package com.guru.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    String str = "";
    TextView tvIdNamaBan, tvIdKodeBan;
    ImageView varImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvIdNamaBan = (TextView) findViewById(R.id.idNamaBan);
        tvIdKodeBan = (TextView) findViewById(R.id.idKodeBan);
        varImage = (ImageView) findViewById(R.id.productImage);


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            String getName = (String) bd.get("name");
            String acceptedUrlImage = (String) bd.get("urltodetail");
            tvIdNamaBan.setText(getName);
            tvIdKodeBan.setText(getName);

            Picasso.with(DetailActivity.this)
                    .load(acceptedUrlImage)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .resize(300,300)
                    .into(varImage);
        }
        ;


    }


}
