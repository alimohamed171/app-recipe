package com.example.testrepo

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class TeamDataAdabter(private val items: List<TeamData>) : RecyclerView.Adapter<TeamDataAdabter.MyViewHolder>()  {  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

    val item = LayoutInflater.from(parent.context).inflate(R.layout.about_row,
        parent,false)
    return MyViewHolder(item)

}

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val  currentItem = items [position]
        holder.image.setImageResource(currentItem.image)
        holder.userName.text = currentItem.name
        holder.userLinkedin.text = currentItem.txtlinkedin
        holder.userGithub.text = currentItem.github
        holder.bindLinkListener(link1 = currentItem.linkedin, link2 = holder.userGithub.text.toString())


    }

    class MyViewHolder( itemView : View)
        : RecyclerView.ViewHolder(itemView) {

        val image : ImageView = itemView.findViewById(R.id.About_imgMain)
        val userName : TextView = itemView.findViewById(R.id.memberName)
        val userLinkedin : TextView = itemView.findViewById(R.id.linkedin)
        val userGithub : TextView = itemView.findViewById(R.id.github)

        fun bindLinkListener(link1:String , link2:String){
            userLinkedin.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link1))
                    itemView.context.startActivity(intent)
            }
            userGithub.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link2))
                itemView.context.startActivity(intent)
            }
        }
    }


}