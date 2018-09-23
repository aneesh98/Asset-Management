package com.apps.teanth.ams;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<users> userList;
    RecyclerView recyclerView;
    //spinner code by aneesh
    //Spinner spin = (Spinner) findViewById(R.id.spinner);
    //spin.setOnItemSelectedListener(this);
    //spin.setAdapter(aa);
    //    String[] country = {"HOD","Faculty","Lab Staff","Departmental Purchase Officer"};
    //ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
    //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating the ArrayAdapter instance having the country list

        //Setting the ArrayAdapter data on the Spinner

        //getting recycler view from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the userlist
        userList = new ArrayList<>();





        //adding some items to our list
        /*userList.add(
                new users(
                        "teanth98@gmail.com",
                        "Tejas",
                        "HOD"
                        ));

        userList.add(
                new users(
                        "askthefactor@gmail.com",
                        "Aneesh",
                        "Lab staff"
                ));

        userList.add(
                new users(
                        "2016.tejas.thakur@ves.ac.in",
                        "xyz",
                        "DPO"
                ));*/
        String url=URL.url+"userlist";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Data",response);
                        try
                        { JSONArray jsonArray=new JSONArray(response);
                          for(int j=0;j<jsonArray.length();j++)
                          {
                              JSONObject UserObj=jsonArray.getJSONObject(j);
                              String email=UserObj.getString("email");
                              String name=UserObj.getString("name");
                              String post="DPO";
                              userList.add(new users(email,name,post));
                          }
                        }
                        catch(JSONException e)
                        {
                            Log.e("Json Error","Something Went Wrong");
                        }



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

            return params;
        }
    };
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);

        //creating recyclerview adapter
        userAdapter adapter = new userAdapter(this, userList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }




/*
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    */
}
