package com.deniz.term_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deniz.term_project.R
import com.deniz.term_project.model.building

class groupedBuildingListAdapter : RecyclerView.Adapter<groupedBuildingListAdapter.groupedBuildingViewHolder>() {

    private val buildingList = mutableListOf<building>()
    class groupedBuildingViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        var buildingId: TextView? = itemView.findViewById(R.id.tvBuildingId)
        var name : TextView? = itemView.findViewById(R.id.tvName)
        var address: TextView? = itemView.findViewById(R.id.tvAddress)
        var lat: TextView? = itemView.findViewById(R.id.tvLat)
        var lng: TextView? = itemView.findViewById(R.id.tvLong)
        var created_at: TextView? = itemView.findViewById(R.id.tvCreated)
        var updated_at: TextView? = itemView.findViewById(R.id.tvUpdated)
        var count: TextView? = itemView.findViewById(R.id.tvCount)
        var matches: TextView? = itemView.findViewById(R.id.tvMatches)
        var completed: TextView? = itemView.findViewById(R.id.tvCompleted)
        var person_count: TextView? = itemView.findViewById(R.id.tvPersoncount)
        var groupId : TextView? = itemView.findViewById(R.id.tvGroupId)
        var groupCount : TextView? = itemView.findViewById(R.id.tvGroupCount)
        fun bind(Building : building) {
            buildingId!!.text   = Building.building_id.toString()
            name!!.text= Building.name
            address!!.text = Building.address
            lat!!.text = Building.lat
            lng!!.text = Building.lng
            created_at!!.text= Building.created_at
            updated_at!!.text=Building.updated_at
            count!!.text = Building.count.toString()
            completed!!.text = Building.completed.toString()
            matches!!.text = Building.matches.toString()
            person_count!!.text = Building.person_count.toString()
            groupId!!.text = Building.group_id.toString()
            groupCount!!.text = Building.group_count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): groupedBuildingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.groupedbuilding_item,parent,false)
        return groupedBuildingViewHolder(view)
    }

    override fun onBindViewHolder(holder: groupedBuildingViewHolder, position: Int) {
        val Building = buildingList[position]
        holder.bind(Building)
    }

    override fun getItemCount(): Int {
        return buildingList.size
    }
    fun listeUpdate(newList: List<building>){
        buildingList.clear()
        buildingList.addAll(newList)
        notifyDataSetChanged()
    }
}