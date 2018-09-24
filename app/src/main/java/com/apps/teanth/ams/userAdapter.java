package com.apps.teanth.ams;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */


public class userAdapter extends RecyclerView.Adapter<userAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<users> userList;
    private Spinner spinner;

    //getting the context and product list with constructor
    public userAdapter(Context mCtx, List<users> userList)
    {

        this.mCtx = mCtx;
        this.userList = userList;
        for(int i=0;i<userList.size();i++)
        {
            Log.e("list ka element"+Integer.valueOf(i).toString(),"Value: "+userList.get(i).getName());
        }

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflat
        // ing and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.userlist, parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        //getting the product of the specified position
        users user = userList.get(position);

        //binding the data with the viewholder views
        holder.EMAIL.setText(user.getEmail());
        holder.NAME.setText(user.getName());
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.UserID.getText().toString().equals(""))
                {
                    Toast.makeText(mCtx,"Enter User ID", Toast.LENGTH_LONG).show();
                    //ipaddress_of_server/ves_hacks/public/api/setUser

                }
                else
                {
                    Toast.makeText(mCtx,"Good To Go", Toast.LENGTH_LONG).show();
                }
            }
        });
        //holder.UserID.setText(user.getUid());
//        holder.spinner.setText(String.valueOf(user.getPositions()));

    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView EMAIL, NAME;
        EditText UserID;
        Button save;

        public ProductViewHolder(View itemView) {
            super(itemView);

            EMAIL = itemView.findViewById(R.id.EMAIL);
            NAME = itemView.findViewById(R.id.NAME);
            UserID= itemView.findViewById(R.id.UserID);
            //spinner = itemView.findViewById(R.id.spinner);
            save=itemView.findViewById(R.id.button);
            /*Spinner spin = (Spinner) itemView.findViewById(R.id.spinner);
            spin.setOnItemSelectedListener();
            spin.setAdapter(aa);
                String[] country = {"HOD","Faculty","Lab Staff","Departmental Purchase Officer"};
                ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/

        }

    }
}