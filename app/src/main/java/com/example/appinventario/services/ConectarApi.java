package com.example.appinventario.services;
import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ConectarApi {

    private RequestQueue requestQueue;
    private Context mainContext;
    private JSONObject jsonResponse;
    private boolean login;
    public ConectarApi(Context context){
        this.mainContext = context;
    }
    public void connect(){
        String url = "http://192.168.0.115:8086/";
        requestQueue = Volley.newRequestQueue(this.mainContext);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
        });
        requestQueue.add(stringRequest);

    }
    public void signinUser(JSONObject user){
        requestQueue = Volley.newRequestQueue(this.mainContext);
        String url = "http://192.168.0.115:8086/signin";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jsonResponse = response;
                        System.out.println("JsonResponse = "+jsonResponse);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                return user.toString().getBytes();
            }
        };
       requestQueue.add(jsonObjectRequest);
    }
   public boolean isLogin(){
        return  this.login;
   }
   public JSONObject getJsonResponse(){
        return  this.jsonResponse;
   }
}
