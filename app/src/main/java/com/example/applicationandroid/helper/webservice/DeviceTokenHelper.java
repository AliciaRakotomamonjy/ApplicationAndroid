package com.example.applicationandroid.helper.webservice;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.applicationandroid.helper.CallBack;
import com.example.applicationandroid.helper.Constante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceTokenHelper {
    public static String url = Constante.API_URL+"/deviceTokens";

    private final Context context;
    private final RequestQueue requestQueue;

    public DeviceTokenHelper(Context context) {
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
                            try {
                                String message = response.getString("message");
                                int status = response.getInt("status");
                                Log.d("SEND NOTIFICATION RESPONSE",message);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });

            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
