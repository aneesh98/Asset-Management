package com.apps.teanth.ams;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private static final String URL_PRODUCTS = "http://192.168.101.1/MyApi/Api.php";
    RecyclerView recyclerView;

    //spinner code by aneesh
    //Spinner spin = (Spinner) findViewById(R.id.spinner);
    //spin.setOnItemSelectedListener(this);
    //spin.setAdapter(aa);
    //    String[] country = {"HOD","Faculty","Lab Staff","Departmental Purchase Officer"};
    //ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
    //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //---------------------------------------------------------------------------------------------
    // adding the uid from edit text view to mysql database
   // private EditText editTextName;
    //private EditText editTextAdd;

//    String name = editTextName.getText().toString();
//    String add = editTextAdd.getText().toString();
    Button Save;
EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Save=(Button)findViewById(R.id.button);
        //Creating the ArrayAdapter instance having the country list

        //Setting the ArrayAdapter data on the Spinner

        //getting recycler view from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the userlist
        userList = new ArrayList<>();

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

                                  //String post="DPO";
                              userList.add(new users(email,name));
                          }
                        }
                        catch(JSONException e)
                        {
                            Log.e("Json Error","Something Went Wrong");
                        }
                        for(int i=0;i<userList.size();i++)
                        Log.e("Main actitivity List "+Integer.valueOf(i).toString(),userList.get(i).getEmail());

                        userAdapter adapter = new userAdapter(MainActivity.this, userList);


                        recyclerView.setAdapter(adapter);
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
        text = (EditText)findViewById(R.id.UserID);

    }


    //String  str=text.getText().toString();
    public void saveToDb(View v)
    {
        if(TextUtils.isEmpty(text.getText()))
        {
            Context context = getApplicationContext();
            CharSequence text = "Enter the UID first";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else
            {
                Context context = getApplicationContext();
                CharSequence text = "UID saved to DB!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

    }
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

