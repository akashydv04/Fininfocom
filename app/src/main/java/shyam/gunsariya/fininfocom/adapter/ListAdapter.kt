package shyam.gunsariya.fininfocom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shyam.gunsariya.fininfocom.databinding.ListItemLayoutBinding
import shyam.gunsariya.fininfocom.model.ListResponse

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    lateinit var listResponse: ArrayList<ListResponse>

    fun updateList(listResponse: ArrayList<ListResponse>){
        this.listResponse = listResponse
    }

    inner class ViewHolder(val binding: ListItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val  layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.binding.name.text = listResponse[position].name
        holder.binding.age.text = listResponse[position].age.toString()
        holder.binding.city.text = listResponse[position].city
    }

    override fun getItemCount(): Int =listResponse.size
}