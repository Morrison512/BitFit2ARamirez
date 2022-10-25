package com.example.bitfit2aramirez

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "BitFitFragment/"

class BitFitFragment : Fragment() {
    private val bitfits = mutableListOf<BitFit>()
    lateinit var bitFitRV: RecyclerView
    lateinit var bitfitAdapter: BitFitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bitfit_fragment, container, false)
        lifecycleScope.launch {
            (activity?.application as BitFItApplication).db.articleDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    BitFit(
                        entity.dayText,
                        entity.hoursSlept,
                    )
                }.also { mappedList ->
                    bitfits.clear()
                    bitfits.addAll(mappedList)
                    bitfitAdapter.notifyDataSetChanged()
                }
            }
        }
        val layoutManager = LinearLayoutManager(context)
        bitFitRV = view.findViewById(R.id.bitFitRV)
        bitFitRV.layoutManager = layoutManager
        bitfitAdapter = BitFitAdapter(view.context, bitfits)
        val newB = view.findViewById<Button>(R.id.newButton)
        newB.setOnClickListener {
            activity?.let {
                val intent = Intent (it, DetailActivity::class.java)
                it.startActivity(intent)
            }
        }
        return view
    }
}