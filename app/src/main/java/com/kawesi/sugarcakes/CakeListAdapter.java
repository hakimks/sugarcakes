package com.kawesi.sugarcakes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kawesi.sugarcakes.data.CakeCategory;

import java.util.ArrayList;

public class CakeListAdapter extends RecyclerView.Adapter<CakeListAdapter.MyViewHolder> {
    ArrayList<Cake> mCakes;

    public CakeListAdapter(ArrayList<Cake> cakes){
        this.mCakes = cakes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.cake_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cake cake = mCakes.get(position);
        holder.bind(cake);

    }

    @Override
    public int getItemCount() {
        return mCakes.size();
    }

    void setCakes(ArrayList<Cake> cakes){
        mCakes = cakes;
        notifyDataSetChanged();

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
         public ImageView cakeImage;
         public TextView cakeName, cakePrice, cakeIngredients, cakeCategory;

         public MyViewHolder(@NonNull View itemView) {
             super(itemView);
             cakeImage = (ImageView) itemView.findViewById(R.id.cake_imageview);
             cakeName = (TextView) itemView.findViewById(R.id.name_textview);
             cakePrice = (TextView) itemView.findViewById(R.id.price_textview);
             cakeIngredients = (TextView) itemView.findViewById(R.id.ingredients_textview);
             cakeCategory = (TextView) itemView.findViewById(R.id.category_textView);
         }

        public void bind(Cake cake) {
            /* Todo set the image using picasso*/
            cakeName.setText(cake.getName());
            cakePrice.setText("Shs: "+ String.valueOf(cake.getPrice()));
            String category = CakeCategory.categoryMap.get(cake.getCategory());

            cakeCategory.setText("Category: " + category);
            cakeIngredients.setText("Special Recipes: "+ cake.getIngredients());
        }
    }
}
