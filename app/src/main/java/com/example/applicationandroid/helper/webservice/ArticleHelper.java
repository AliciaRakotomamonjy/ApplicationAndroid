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

public class ArticleHelper {

    public static String url = Constante.API_URL+"/articles";
    public static String urlFavori = Constante.API_URL+"/favoris";

    private final Context context;
    private final RequestQueue requestQueue;

    public ArticleHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void getListArticle(String categorieId,final CallBack callBack){
        try {
            String params = String.format("?categorieId=%s",categorieId);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url+params, null,
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

    public void rechercheArticle(String titre,final CallBack callBack){
        try {
            String params = String.format("?titre=%s",titre);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url+params, null,
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

    public void getListArticleFavori(String idUser,final CallBack callBack){
        try {
            String params = String.format("?idUser=%s",idUser);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlFavori+params, null,
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
