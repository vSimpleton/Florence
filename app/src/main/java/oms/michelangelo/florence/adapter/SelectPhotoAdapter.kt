package oms.michelangelo.florence.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_select_photo.view.*
import oms.michelangelo.florence.bean.MediaData
import oms.michelangelo.florence.R

/**
 * Created by Sherry on 2020/5/4
 */

class SelectPhotoAdapter(private var context: Context) :
    RecyclerView.Adapter<SelectPhotoAdapter.ViewHolder>() {

    private var mLists: List<MediaData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_select_photo, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = mLists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(mLists[position].thumbPath).into(holder.itemView.ivPhoto)
    }

    fun setData(lists: List<MediaData>) {
        this.mLists = lists
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}