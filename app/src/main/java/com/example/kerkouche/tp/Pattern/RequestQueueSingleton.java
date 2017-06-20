package com.example.kerkouche.tp.Pattern;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Hakim Mokeddem on 06/05/2017.
 */

public class RequestQueueSingleton {

    private static RequestQueueSingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context context;

    private RequestQueueSingleton(Context context) {
        this.context = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestQueueSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

}
