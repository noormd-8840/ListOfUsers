package com.example.listofusers.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.listofusers.Adapters.UsersAdapter;
import com.example.listofusers.Model.Users;
import com.example.listofusers.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myTag";
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    private ArrayList<Users> usersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recyclerView);

        String url = "https://reqres.in/api/users?page=2";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d(TAG, "onResponse: " + response.getInt("total") + ":" + response.getInt("page") + ":" + response.getInt("per_page")
                                    + ":" + response.getInt("total_pages"));
                            usersList.add(new Users(response.getInt("page"), response.getInt("per_page"), response.getInt("total"),
                                    response.getInt("total_pages")));


                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                Log.d(TAG, "onResponse: " + data.getInt("id") + ":" + data.getString("email") + ":" + data.getString("first_name") + ":" +
                                        data.getString("last_name") + ":" + data.getString("avatar"));

                                addData(data.getInt("id"), data.getString("email"), data.getString("first_name"),
                                        data.getString("last_name"), data.getString("avatar"));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: Something went wrong");

                    }
                });
        requestQueue.add(jsonObjectRequest);

        usersAdapter = new UsersAdapter(this, usersList);
        recyclerView.setAdapter(usersAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        addData(7, "michael.lawson@reqres.in", "Michael", "Lawson", "https://reqres.in/img/faces/7-image.jpg");
    }

    private void addData(int id, String email, String first_name, String last_name, String avatar) {
        usersList.add(new Users(id, email, first_name, last_name, avatar));
    }

}