package com.deniz.term_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deniz.term_project.R
import com.deniz.term_project.model.team

class teamListAdapter: RecyclerView.Adapter<teamListAdapter.teamViewHolder>(){
    private val teamList = mutableListOf<team>()
    class teamViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        var TeamId : TextView? = itemView.findViewById(R.id.tvTeamId)
        var Name : TextView? = itemView.findViewById(R.id.tvName)
        var Lat: TextView? = itemView.findViewById(R.id.tvLat)
        var Lng: TextView? = itemView.findViewById(R.id.tvLong)
        var Created_at: TextView? = itemView.findViewById(R.id.tvCreated)
        var Updated_at: TextView? = itemView.findViewById(R.id.tvUpdated)
        var BuildingId : TextView? = itemView.findViewById(R.id.tvBuildingId)
        var Count : TextView? = itemView.findViewById(R.id.tvCount)


        fun bind(Team : team) {
            TeamId!!.text   = Team.team_id.toString()
            Name!!.text= Team.name
            Lat!!.text = Team.lat
            Lng!!.text = Team.lng
            Created_at!!.text= Team.created_at
            Updated_at!!.text=Team.updated_at
            Count!!.text = Team.count.toString()
            BuildingId!!.text = Team.building_id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): teamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.team_item,parent,false)
        return teamViewHolder(view)
    }

    override fun onBindViewHolder(holder: teamViewHolder, position: Int) {
        val Team = teamList[position]
        holder.bind(Team)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }
    fun listeUpdate(newList: List<team>){
        teamList.clear()
        teamList.addAll(newList)
        notifyDataSetChanged()
    }


}