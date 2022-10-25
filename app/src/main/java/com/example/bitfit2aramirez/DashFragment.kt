package com.example.bitfit2aramirez

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Headers
import org.json.JSONException

class DashFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dash_fragment, container, false)

        val dayCount = view.findViewById<TextView>(R.id.Daystotal)
        val avgSleep = view.findViewById<TextView>(R.id.sleepAV)

        lifecycleScope.launch(Dispatchers.IO) {
            val c = (activity?.application as BitFItApplication).db.articleDao().getRowCount()
            val a = (activity?.application as BitFItApplication).db.articleDao().averageSleep()
            dayCount.text = c.toString()
            avgSleep.text = a.toString()
        }
        val newB = view.findViewById<Button>(R.id.newButton2)
        newB.setOnClickListener {
            activity?.let {
                val intent = Intent (it, DetailActivity::class.java)
                it.startActivity(intent)
            }
        }
        return view
    }


}