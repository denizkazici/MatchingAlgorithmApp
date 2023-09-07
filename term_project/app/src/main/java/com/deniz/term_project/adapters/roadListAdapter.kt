package com.deniz.term_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deniz.term_project.R
import com.deniz.term_project.model.road

class roadListAdapter : RecyclerView.Adapter<roadListAdapter.roadViewHolder>(){
    private val roadList = mutableListOf<road>()
    class roadViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        var RoadId : TextView? = itemView.findViewById(R.id.tvRoadId)
        var Name : TextView? = itemView.findViewById(R.id.tvName)
        var Lat: TextView? = itemView.findViewById(R.id.tvLat)
        var Lng: TextView? = itemView.findViewById(R.id.tvLong)
        var Created_at: TextView? = itemView.findViewById(R.id.tvCreated)
        var Updated_at: TextView? = itemView.findViewById(R.id.tvUpdated)



        fun bind(Road : road) {
            RoadId!!.text   = Road.road_id.toString()
            Name!!.text= Road.name
            Lat!!.text = Road.lat
            Lng!!.text = Road.lng
            Created_at!!.text= Road.created_at
            Updated_at!!.text=Road.updated_at
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): roadViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.road_item,parent,false)
        return roadViewHolder(view)
    }

    override fun onBindViewHolder(holder: roadViewHolder, position: Int) {
        val Road = roadList[position]
        holder.bind(Road)
    }

    override fun getItemCount(): Int {
        return roadList.size
    }
    fun listeUpdate(newList: List<road>){
        roadList.clear()
        roadList.addAll(newList)
        notifyDataSetChanged()
    }
}