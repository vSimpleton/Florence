package oms.michelangelo.florence

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_select_photo.view.*

/**
 * Created by Sherry on 2020/5/4
 */

class SelectPhotoAdapter(var context: Context) :
    RecyclerView.Adapter<SelectPhotoAdapter.ViewHolder>() {

    private lateinit var mLists: ArrayList<Drawable>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_select_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mLists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(mLists[position]).into(holder.itemView.ivPhoto)
    }

    fun setData(lists: ArrayList<Drawable>) {
        this.mLists = lists
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}