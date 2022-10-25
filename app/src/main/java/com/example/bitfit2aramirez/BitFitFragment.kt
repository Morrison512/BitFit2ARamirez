package com.example.bitfit2aramirez

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    lateinit var newB: Button
    lateinit var bitFitRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout.bitfit_fragment, container, false)
        val recyclerView = view.findViewById<View>(R.id.bitFitRV) as RecyclerView
        val context = view.context
        bitFitRV.layoutManager = LinearLayoutManager(context)
        updateAdapter(recyclerView)
        return view
    }

    private fun updateAdapter(recyclerView: RecyclerView) {
        val bitfitAdapter = BitFitAdapter(this, bitfits)
        bitFitRV.adapter = bitfitAdapter

        newB = findViewById(R.id.newButton)
        newB.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            this.startActivity(intent)
        }
        lifecycleScope.launch {
            (application as BitFItApplication).db.articleDao().getAll().collect { databaseList ->
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

        val bitFit = intent.getSerializableExtra("EXTRA_ENTRY") as BitFit?

        if(bitFit != null) {
            Log.d(TAG, "got extra")
            lifecycleScope.launch(Dispatchers.IO) {
                (application as BitFItApplication).db.articleDao().insert(
                    BitFitEntity(
                        dayText = bitFit.dayText,
                        hoursSlept = bitFit.hoursSlept
                    )
                )
            }
        }
    }
}