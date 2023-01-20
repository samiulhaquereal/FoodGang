package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodorder.halper.ManagementCart;
import com.example.foodorder.model.popularmodel;

public class showdetails extends AppCompatActivity {
    private TextView addtocardbtn,titletxt,feetxt,descrtxt,numbertxt;
    private ImageView plusbtn,minusbtn,picfood;
    private popularmodel object;
    private int numberoder=1;
    private ManagementCart managementCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetails);

        managementCart = new ManagementCart(this);

        initview();
        getBundle();
    }

    private void getBundle() {
        object = (popularmodel) getIntent().getSerializableExtra("object");

        int drawableResource = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());

        Glide.with(this).load(drawableResource).into(picfood);

        titletxt.setText(object.getTitle());
        feetxt.setText("৳"+object.getFee());
        descrtxt.setText(object.getDescription());
        numbertxt.setText(String.valueOf(numberoder));


        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberoder=numberoder+1;
                numbertxt.setText(String.valueOf(numberoder));

            }
        });
        minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberoder>1){
                    numberoder=numberoder-1;
                }
                numbertxt.setText(String.valueOf(numberoder));

            }
        });

        addtocardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberIncart(numberoder);
                managementCart.insertfood(object);
            }
        });


    }

    private void initview() {

    addtocardbtn = findViewById(R.id.addtocardid);
    titletxt = findViewById(R.id.titletextid);
    feetxt = findViewById(R.id.pricetextid);
    descrtxt = findViewById(R.id.descriptionid);
    numbertxt = findViewById(R.id.countid);
    plusbtn = findViewById(R.id.plusid);
    minusbtn= findViewById(R.id.minusid);
    picfood = findViewById(R.id.piccid);
    }
}