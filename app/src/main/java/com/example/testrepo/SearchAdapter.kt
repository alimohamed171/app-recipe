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

class SearchAdapter (private val data: List<Meal>, private val context: Context, private val view: View): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.search_favourite_single_row, parent, false)
        return SearchViewHolder(row)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
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
        mealFlag(data[position].strArea, view, holder.areaIcon)
        holder.itemView.setOnClickListener {
            val action =
                SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                    data[position].strMealThumb,
                    data[position].strMeal,
                    data[position].strInstructions,
                    data[position].strYoutube,
                    data[position].idMeal
                )
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun mealFlag (area: String, view: View, areaIcon: ImageView) {
        var flag: Int = 0
        when (area) {
            "British" -> flag = R.drawable.british_flag
            "Portuguese" -> flag = R.drawable.portugal_flag
            "Greek" -> flag = R.drawable.greece_flag
            "Moroccan" -> flag = R.drawable.morocco_flag
            "Egyptian" -> flag = R.drawable.egyptian_flag
            "Turkish" -> flag = R.drawable.turkey_flag
            "Tunisian" -> flag = R.drawable.tunisian_flag
            "Japanese" -> flag = R.drawable.japanese_flag
            "Spanish" -> flag = R.drawable.spain_flag
            "Filipino" -> flag = R.drawable.filipino_flag
            "Croatian" -> flag = R.drawable.croatian_flag
            "Malaysian" -> flag = R.drawable.malaysian_flag
            "Irish" -> flag = R.drawable.irish_flag
            "Polish" -> flag = R.drawable.polish_flag
            "Vietnamese" -> flag = R.drawable.vietnam_flag
            "Dutch" -> flag = R.drawable.dutch_flag
            "Italian" -> flag = R.drawable.italian_flag
            "Chinese" -> flag = R.drawable.chinese_flag
            "Jamaican" -> flag = R.drawable.jamaican_flag
            "Canadian" -> flag = R.drawable.canadian_flag
            "French" -> flag = R.drawable.france_flag
            "Russian" -> flag = R.drawable.russian_flag
            "Mexican" -> flag = R.drawable.mexico_flag
            "American" -> flag = R.drawable.amirican_flag
            "Indian" -> flag = R.drawable.indian_flag
        }

        Glide.with(context)
            .load(flag)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loadin_image)
                    .error(R.drawable.broken_image))
            .into(areaIcon)
    }

    class SearchViewHolder(private val row: View) : RecyclerView.ViewHolder(row) {
        val image: ImageView = row.findViewById(R.id.imgMain)
        val name: TextView = row.findViewById(R.id.textName)
        val category: TextView = row.findViewById(R.id.textCategory)
        val area: TextView = row.findViewById(R.id.textArea)
        val areaIcon: ImageView = row.findViewById(R.id.areaIcon)
    }
}