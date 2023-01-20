package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodorder.adapter.CategoriesAdapter;
import com.example.foodorder.adapter.PopularAdapter;
import com.example.foodorder.model.Categoriesmodel;
import com.example.foodorder.model.popularmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView catagories , popular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        category();
        popular();
        bottomNavigation();

    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartbtn);
        LinearLayout homebtn = findViewById(R.id.homebtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,cart.class));
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,dashboard.class));
            }
        });
    }

    private void category() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        catagories= findViewById(R.id.list1);
        catagories.setLayoutManager(linearLayoutManager);

        ArrayList<Categoriesmodel> categories = new ArrayList<>();
        categories.add(new Categoriesmodel("Pizza","cat_1"));
        categories.add(new Categoriesmodel("Burger","cat_2"));
        categories.add(new Categoriesmodel("Hotdog","cat_3"));
        categories.add(new Categoriesmodel("Drink","cat_4"));
        categories.add(new Categoriesmodel("Donut","cat_5"));


        adapter=new CategoriesAdapter(categories);
        catagories.setAdapter(adapter);
    }

    private void popular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        popular= findViewById(R.id.list2);
        popular.setLayoutManager(linearLayoutManager);

        ArrayList<popularmodel> foodlist= new ArrayList<>();
        foodlist.add(new popularmodel("Papparoni Pizza","pizza","slices papparoni,mojorela cheese,Fresh Oregano",599.00));
        foodlist.add(new popularmodel("Cheese Burger","burger","beef , cheese , Lettuce , tomato",299.00));
        foodlist.add(new popularmodel("Vegetable Pizza","pop_3","olive oil , vegetable oil , cherry tomamto",499.00));

        adapter2 = new PopularAdapter(foodlist);
        popular.setAdapter(adapter2);


    }


}