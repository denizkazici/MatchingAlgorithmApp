package com.deniz.term_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.deniz.term_project.R
import com.deniz.term_project.model.user

class userListAdapter : RecyclerView.Adapter<userListAdapter.userViewHolder>(){

    private val userList = mutableListOf<user>()
    class userViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var id: TextView? = itemView.findViewById(R.id.user_id)
        var name: TextView? = itemView.findViewById(R.id.user_name)
        var password: TextView? = itemView.findViewById(R.id.user_password)
        var teamid: TextView? = itemView.findViewById(R.id.user_teamid)
        var age: TextView? = itemView.findViewById(R.id.user_age)
        var info: TextView? = itemView.findViewById(R.id.user_info)
        var lat: TextView?  = itemView.findViewById(R.id.user_lat)
        var long:TextView? = itemView.findViewById(R.id.user_long)
        var created_at: TextView? = itemView.findViewById(R.id.user_created)
        var updated_at: TextView? = itemView.findViewById(R.id.user_updated)
        var buildingid: TextView? = itemView.findViewById(R.id.user_building)
        var usertype: TextView? = itemView.findViewById(R.id.user_type)

        fun bind(User: user) {
            id!!.text   = User.user_id.toString()
            name!!.text= User.name
            password!!.text= User.password
            teamid!!.text = User.team_id.toString()
            age!!.text = User.age.toString()
            info!!.text = User.info
            lat!!.text = User.lat
            long!!.text = User.lng
            created_at!!.text= User.created_at
            updated_at!!.text=User.updated_at
            buildingid!!.text=User.building_id.toString()
            usertype!!.text = User.user_type.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_item,parent,false)
        return userViewHolder(view)
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val User = userList[position]
        holder.bind(User)
        /*holder.id!!.text = userList.get(position).user_id.toString()
        holder.name!!.text = userList.get(position).name
        holder.password!!.text = userList.get(position).password
        holder.teamid!!.text = userList.get(position).team_id.toString()
        holder.age!!.text = userList.get(position).age.toString()
        holder.info!!.text = userList.get(position).info
        holder.lat!!.text = userList.get(position).lat
        holder.long!!.text = userList.get(position).lng
        holder.created_at!!.text = userList.get(position).created_at
        holder.updated_at!!.text = userList.get(position).updated_at
        holder.buildingid!!.text = userList.get(position).building_id.toString()
        holder.usertype!!.text = userList.get(position).user_type.toString()*/


    }

    override fun getItemCount(): Int {
        return userList.size
    }
    fun listeUpdate(newUserList: List<user>){
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }
}