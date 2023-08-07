package com.example.applicationandroid.helper;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface CallBack {
    void onSuccess(JSONObject response);
    void onError(VolleyError error);
}
