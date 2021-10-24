package com.mohit.mockk_coroutines.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohit.mockk_coroutines.R
import com.mohit.mockk_coroutines.data.repository.Repository


/**
 * ListAdapter
 * @desc - View for displaying data in recycler view
 * @param listData - List of items to display
 * */
class ListAdapter(
    private var mCtx: Context,
    private var listData: ArrayList<Repository>,
) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.listview_layout, parent, false)
        return ListViewHolder(view)

    }

    /*Set data in view*/
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]

        onbind(holder, data) //set data in list row

    }

    private fun onbind(
        holder: ListViewHolder,
        data: Repository
    ) {
        holder.tvTitle.text = data.repositoryName
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.title)
    }



}

