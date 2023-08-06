package com.example.testrepo

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testrepo.model.Meal

class HomeAdapter (private val data: List<Meal>, private val context: Context, private val view: View): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.home_single_row, parent, false)
        return HomeViewHolder(row)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        Glide.with(context)
            .load(data[position].strMealThumb)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loadin_image)
                    .error(R.drawable.broken_image))
            .into(holder.image)
        holder.name.text = data[position].strMeal
        holder.category.text = data[position].strCategory
        holder.area.text = data[position].strArea
        holder.itemView.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    data[position].strMealThumb,
                    data[position].strMeal,
                    data[position].strInstructions,
                    data[position].strYoutube)
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class HomeViewHolder(private val row: View) : RecyclerView.ViewHolder(row) {
        val image: ImageView = row.findViewById(R.id.imgMain)
        val name: TextView = row.findViewById(R.id.textName)
        val category: TextView = row.findViewById(R.id.textCategory)
        val area: TextView = row.findViewById(R.id.textArea)
    }
}