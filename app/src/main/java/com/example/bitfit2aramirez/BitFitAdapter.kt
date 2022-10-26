package com.example.bitfit2aramirez

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val BITFIT_EXTRA = "BitFit_Extra"
private const val TAG = "BitFitAdapter"

class BitFitAdapter(private val context: Context,private val BitLists: List<BitFit>):
    RecyclerView.Adapter<BitFitAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dayTV: TextView
        var sleepTV: TextView


        init {
            dayTV = itemView.findViewById(R.id.enterdDayTV)
            sleepTV = itemView.findViewById(R.id.enterdHoursSlept)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val wLView = inflater.inflate(R.layout.bit_fit_item, parent, false)

        return ViewHolder(wLView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bitList = BitLists[position]

        holder.dayTV.text = bitList.dayText
        holder.sleepTV.text = bitList.hoursSlept.toString()
    }
    override fun getItemCount() = BitLists.size
}