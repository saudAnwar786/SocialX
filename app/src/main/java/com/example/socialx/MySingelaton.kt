package com.example.socialx

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MySingelaton constructor(context: Context) {
    companion object{
        @Volatile
        private var INSTANCE : MySingelaton? = null

        fun getInstance(context: Context)=
            INSTANCE?: synchronized(this){
                INSTANCE?: MySingelaton(context).also {
                    INSTANCE = it
                }
            }
    }
    private val requestQue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun<T> addToRequestQueue(req: Request<T>){
        requestQue.add(req)
    }

}