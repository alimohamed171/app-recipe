package com.example.testrepo

import android.content.Context
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

class FavoriteAdapter(private val context: Context, private val view: View): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private lateinit var data: List<Meal>
    class FavoriteViewHolder(val row: View): RecyclerView.ViewHolder(row)
    {
        val image: ImageView = row.findViewById(R.id.imgMain)
        val name: TextView = row.findViewById(R.id.textName)
        val category: TextView = row.findViewById(R.id.textCategory)
        val area: TextView = row.findViewById(R.id.textArea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.search_favourite_single_row, parent, false)
        return FavoriteAdapter.FavoriteViewHolder(row)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
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
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(
                    data[position].strMealThumb,
                    data[position].strMeal,
                    data[position].strInstructions,
                    data[position].strYoutube)
            view.findNavController().navigate(action)
        }
    }

    fun setData(data: List<Meal>)
    {
        this.data = data
        notifyDataSetChanged()
    }
}