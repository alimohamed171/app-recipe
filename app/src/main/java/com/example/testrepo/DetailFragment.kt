package com.example.testrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testrepo.network.APIClient
import com.example.testrepo.repo.MealRepository
import com.example.testrepo.user_data.UserDatabase
import com.example.testrepo.user_data.UserRepository
import com.example.testrepo.viewModel.MealViewModel
import com.example.testrepo.viewModel.MealViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class DetailFragment : Fragment() {
    private lateinit var youTubeVideoID: String
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var checkBox: CheckBox
    private var mealViewModel: MealViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showReachedArgs(view)
        checkBox = view.findViewById(R.id.checkBox_favourite)
        if(mealViewModel == null)
        {
            val userDao = UserDatabase.getDatabase(this.requireContext()).userDao()
            val mealDataDao = UserDatabase.getDatabase(this.requireContext()).mealDataDao()
            val favoriteDao = UserDatabase.getDatabase(this.requireContext()).favoritesDao()
            mealViewModel = ViewModelProvider(this, MealViewModelFactory(MealRepository(APIClient), UserRepository(userDao, favoriteDao, mealDataDao))).get(MealViewModel::class.java)
        }

        if(mealViewModel?.isFavorite(args.mealId, SharedPrefs.getCurrentUser()) != null)
        {
            checkBox.isChecked = true
        }
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                mealViewModel?.insertMeal(args.mealId, args.name)
                mealViewModel?.addToFavorites(args.mealId, SharedPrefs.getCurrentUser())
            }
            else
            {
                showAlertDialog()
            }
        }
    }

    private fun showReachedArgs(view: View) {
        val image: ImageView = view.findViewById(R.id.imgFood)
        val title: TextView = view.findViewById(R.id.textTitle)
        val details: TextView = view.findViewById(R.id.textDetails)


        Glide.with(this.requireActivity())
            .load(args.image)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loadin_image)
                    .error(R.drawable.broken_image))
            .into(image)
        title.text = args.name
        details.text = args.details

        youTubeVideoID = args.video
        controlYouTubePlayer(view, youTubeVideoID)
    }

    private fun controlYouTubePlayer(view: View, videoID: String) {
        // add YouTubePlayerView as a lifecycle observer of its parent
        val youTubePlayerView: YouTubePlayerView = view.findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)

        // open video by user
        youTubePlayerView.addYouTubePlayerListener(YouTubePlayerListener(videoID))
    }

    class YouTubePlayerListener(videoID: String): AbstractYouTubePlayerListener(){
        private val youTubeVideoID = getYouTubeId(videoID)
        override fun onReady(youTubePlayer: YouTubePlayer) {
            super.onReady(youTubePlayer)
            youTubePlayer.loadVideo(youTubeVideoID, 0F)
        }

        private fun getYouTubeId (youTubeUrl: String): String {
            var id = ""
            for (i in youTubeUrl.length - 1 downTo 0) {
                if (youTubeUrl[i] == '=')
                    break
                id += youTubeUrl[i]
            }
            return id.reversed()
        }
    }

    private fun showAlertDialog()
    {
        val alertDialog = AlertDialog.Builder(this.requireContext())
            .setTitle("Alert")
            .setMessage("Are You Sure You Want to Remove this Meal From Favorites?")
            .setPositiveButton("Yes"){ dialog, which ->
                mealViewModel?.removeFromFavorites(args.mealId, SharedPrefs.getCurrentUser())
                dialog.dismiss()
            }
            .setNegativeButton("No"){ dialog, which ->
                checkBox.isChecked = true
                dialog.dismiss()
            }
            .create()
        alertDialog.show()
    }
}