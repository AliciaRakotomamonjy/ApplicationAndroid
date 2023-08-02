package com.example.applicationandroid.helper.webservice;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.applicationandroid.helper.Constante;

import org.json.JSONException;
import org.json.JSONObject;

public class DeviceTokenAPI {
    public static String url = Constante.API_URL+"/deviceTokens";

    private final Context context;
    private final RequestQueue requestQueue;

    public DeviceTokenAPI(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void sendDeviceToken(String deviceToken) throws JSONException {
        try {
            JSONObject requestBody = new JSONObject();
            requestBody.put("token", deviceToken);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("send device token","token send successfully");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("send device token",error.getMessage());
                        }
                    });

            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
