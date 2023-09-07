package com.deniz.term_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deniz.term_project.R
import com.deniz.term_project.model.building

import com.deniz.term_project.model.group

class groupListAdapter : RecyclerView.Adapter<groupListAdapter.groupViewHolder>() {

    private val groupList = mutableListOf<group>()
    class groupViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        var buildingId: TextView? = itemView.findViewById(R.id.tvBuildingId)
        var groupId : TextView? = itemView.findViewById(R.id.tvGroupId)
        var id : TextView? = itemView.findViewById(R.id.tvId)
        var group_count : TextView? = itemView.findViewById(R.id.tvGroupCount)
        var created_at: TextView? = itemView.findViewById(R.id.tvCreated)
        var updated_at: TextView? = itemView.findViewById(R.id.tvUpdated)
        fun bind(Group : group) {
            buildingId!!.text   = Group.building_id.toString()
            groupId!!.text = Group.group_id.toString()
            id!!.text = Group.id.toString()
            group_count!!.text = Group.range_value.toString()
            created_at!!.text= Group.created_at
            updated_at!!.text=Group.updated_at
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): groupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.group_item,parent,false)
        return groupListAdapter.groupViewHolder(view)
    }

    override fun onBindViewHolder(holder: groupViewHolder, position: Int) {
        val Group = groupList[position]
        holder.bind(Group)
    }

    override fun getItemCount(): Int {
        return groupList.size
    }
    fun listeUpdate(newList: List<group>){
        groupList.clear()
        groupList.addAll(newList)
        notifyDataSetChanged()
    }
}