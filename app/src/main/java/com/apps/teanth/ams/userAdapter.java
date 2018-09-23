package com.apps.teanth.ams;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */


public class userAdapter extends RecyclerView.Adapter<userAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<users> userList;

    //getting the context and product list with constructor
    public userAdapter(Context mCtx, List<users> userList) {

        this.mCtx = mCtx;
        this.userList = userList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.userlist, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        users user = userList.get(position);

        //binding the data with the viewholder views
        holder.EMAIL.setText(user.getEmail());
        holder.NAME.setText(user.getName());
//        holder.spinner.setText(String.valueOf(user.getPositions()));




    }


    @Override
    public int getItemCount() {
        return userList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView EMAIL, NAME, spinner;

        public ProductViewHolder(View itemView) {
            super(itemView);

            EMAIL = itemView.findViewById(R.id.EMAIL);
            NAME = itemView.findViewById(R.id.NAME);
            //spinner = itemView.findViewById(R.id.spinner);

        }
    }
}