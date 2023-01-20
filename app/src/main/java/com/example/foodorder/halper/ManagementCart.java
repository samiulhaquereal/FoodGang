package com.example.foodorder.halper;

import android.content.Context;
import android.widget.Toast;

import com.example.foodorder.Interface.ChangeNumberItemsListener;
import com.example.foodorder.model.popularmodel;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertfood(popularmodel item){

        ArrayList<popularmodel> listFood= getlistCart();
                boolean exitAlready = false;
                int n=0;
                for (int i=0;i<listFood.size();i++){
                    if(listFood.get(i).getTitle().equals(item.getTitle())){
                        exitAlready = true;
                        n = i;
                        break;
                    }
                }
                if(exitAlready){
                    listFood.get(n).setNumberIncart(item.getNumberIncart());
                }
                else {
                    listFood.add(item);
                }
                tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Added To Your Card", Toast.LENGTH_SHORT).show();

    }
    public ArrayList<popularmodel> getlistCart(){
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<popularmodel>listFood , int position ,ChangeNumberItemsListener changeNumberItemsListener){
        listFood.get(position).setNumberIncart(listFood.get(position).getNumberIncart()+1);
        tinyDB.putListObject("CartList",listFood);
        changeNumberItemsListener.changed();
    }
    public void minusNumberFood(ArrayList<popularmodel>listFood , int position ,ChangeNumberItemsListener changeNumberItemsListener){

        if(listFood.get(position).getNumberIncart()==1){
            listFood.remove(position);
        }else{
            listFood.get(position).setNumberIncart(listFood.get(position).getNumberIncart()-1);
        }
        tinyDB.putListObject("CartList",listFood);
        changeNumberItemsListener.changed();
    }


    public Double getTotalFee(){

        ArrayList<popularmodel> listfood=getlistCart();
        double fee=0;
        for (int i = 0; i < listfood.size();i++){
            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumberIncart());
        }

        return fee;

    }

}
