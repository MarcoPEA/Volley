package com.example.volleyimagenes;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

public class Volley {
    private static Volley mVolley = null;

    //Este es el objeto que utilizara la aplicación
    private RequestQueue mRequestQueue;

    private Volley(Context context){
        mRequestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
    }

    public static Volley getInstance(Context context){
        if(mVolley == null){
            mVolley = new Volley(context);
        }
        return mVolley;
    }

    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getmRequestQueue().add(req);
    }

}
