package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodorder.Interface.ChangeNumberItemsListener;
import com.example.foodorder.adapter.Cartlistadapter;
import com.example.foodorder.halper.ManagementCart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class cart extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagementCart managementCart;
    TextView totalFeetxt, taxtxt , deliverytxt , totaltxt , empytxt ;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        intView();
        intlist();
        Calculatecart();
        bottomNavigation();

    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartbtn);
        LinearLayout homebtn = findViewById(R.id.homebtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cart.this,cart.class));
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cart.this,dashboard.class));
            }
        });
    }

    private void intView() {
        recyclerView = findViewById(R.id.viewid);
        totalFeetxt = findViewById(R.id.totalfeetext1);
        taxtxt = findViewById(R.id.totalfeetext2);
        deliverytxt = findViewById(R.id.totalfeetext3);
        totaltxt = findViewById(R.id.totalfeetext4);
        empytxt = findViewById(R.id.emptytxt);
        scrollView = findViewById(R.id.scrollView3);
    }
    private void intlist(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new Cartlistadapter(managementCart.getlistCart(),this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {

                Calculatecart();

            }
        });

        recyclerView.setAdapter(adapter);
        if(managementCart.getlistCart().isEmpty()){
            empytxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            empytxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }


    }

    private void Calculatecart(){
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee()*percentTax)*100)/100;

        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) /100;
        double itemtotal = Math.round(managementCart.getTotalFee() * 100) /100;

        totalFeetxt.setText("৳ "+itemtotal);
        taxtxt.setText("৳ "+tax);
        deliverytxt.setText("৳ "+delivery);
        totaltxt.setText("৳ "+total);
    }
}