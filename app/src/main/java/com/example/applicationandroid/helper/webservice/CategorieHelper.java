package com.example.applicationandroid.helper.webservice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.applicationandroid.helper.CallBack;
import com.example.applicationandroid.helper.Constante;

import org.json.JSONObject;

public class CategorieHelper {

    public static String url = Constante.API_URL+"/categories";

    private final Context context;
    private final RequestQueue requestQueue;

    public CategorieHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void getListCategorie(final CallBack callBack){
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callBack.onSuccess(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callBack.onError(error);
                        }
                    });

            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
