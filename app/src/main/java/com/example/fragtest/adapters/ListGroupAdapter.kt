package com.example.fragtest.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragtest.data.MusicGroups
import com.example.fragtest.databinding.ItemFragmentListGroupBinding
import com.example.fragtest.databinding.ItemTestListGroupBinding

class ListGroupAdapter(
    var items: List<MusicGroups>,
    val onClickListener: (Int) -> Unit): RecyclerView.Adapter<MusicGroupViewHolder>()
{
    /*
    * Estas 3 funciones son implementadas onCreateViewHolder onBindViewHolder getItemCount
    *
    * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicGroupViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTestListGroupBinding.inflate(layoutInflater,parent,false)
        return MusicGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicGroupViewHolder, position: Int)
    {
        val item = items[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickListener(position) }
    }

    override fun getItemCount(): Int {return items.size}

    /**/
    fun updateItems(items: List<MusicGroups>) {
        this.items = items
        notifyDataSetChanged()
    }
}

/**/
class MusicGroupViewHolder(
    val binding: ItemTestListGroupBinding):
    RecyclerView.ViewHolder(binding.root)
{
        /**/
        fun render(musicGroup: MusicGroups){
            binding.idTvNameGroup.text = musicGroup.group
            binding.idTvHistory.text = musicGroup.history
            Log.i("API", musicGroup.image ?: "")
        }
//
}