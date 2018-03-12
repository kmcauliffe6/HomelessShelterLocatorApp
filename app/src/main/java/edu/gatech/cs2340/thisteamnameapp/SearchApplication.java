package edu.gatech.cs2340.thisteamnameapp;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
/**
 * Created by paigemca on 3/12/18.
 */

public class SearchApplication extends Application {

        public static final String TAG = SearchApplication.class
                .getSimpleName();

        private RequestQueue mRequestQueue;

        private static SearchApplication mInstance;

        @Override
        public void onCreate() {
            super.onCreate();
            mInstance = this;
        }

        public static synchronized SearchApplication getInstance() {
            return mInstance;
        }

        public RequestQueue getRequestQueue() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            }

            return mRequestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req, String tag) {
            // set the default tag if tag is empty
            req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
            getRequestQueue().add(req);
        }

        public <T> void addToRequestQueue(Request<T> req) {
            req.setTag(TAG);
            getRequestQueue().add(req);
        }

        public void cancelPendingRequests(Object tag) {
            if (mRequestQueue != null) {
                mRequestQueue.cancelAll(tag);
            }
        }
    }
