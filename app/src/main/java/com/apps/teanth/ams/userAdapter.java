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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Belal on 10/18/2017.
 */


public class userAdapter extends RecyclerView.Adapter<userAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<users> userList;
    private Spinner spinner;
    private String uname;
    private String uemail;
    private String UID;
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
    users user;

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        //getting the product of the specified position
      user = userList.get(position);

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
                else if(holder.UserID.getText().toString().length()!=5)
                { Toast.makeText(mCtx,"User ID must be strictly of 5 digits", Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(mCtx,"Good To Go", Toast.LENGTH_LONG).show();
                    uname=user.getName();
                    uemail=user.getEmail();
                    UID=holder.UserID.getText().toString();
                    String url=URL.url+"setUser";
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("Data",response);
                                    Toast.makeText(mCtx,"Approved",Toast.LENGTH_LONG).show();




                                }
                            }

                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error","Something Went Wrong");
                        }
                    }){
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {

                            Map<String,String> params=new HashMap<String, String>();
                            params.put("email",uemail);
                            params.put("uid",UID);
                            return params;
                        }
                    };
                    MySingleton.getInstance(mCtx).addToRequestQueue(stringRequest);



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