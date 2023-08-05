package com.example.testrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controlYouTubePlayer(view)
    }

    private fun onCheckedFavouriteButton() {
//        checkBox.setOnCheckedChangeListener(checkBox, isChecked ->
//        if (isChecked)
//            TODO()
//        else
//            TODO()
//        )
    }

    private fun controlYouTubePlayer(view: View) {
        // add YouTubePlayerView as a lifecycle observer of its parent
        val youTubePlayerView: YouTubePlayerView = view.findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)

        // open video by user
        youTubePlayerView.addYouTubePlayerListener(YouTubePlayerListener())
    }

    class YouTubePlayerListener(): AbstractYouTubePlayerListener(){
        override fun onReady(youTubePlayer: YouTubePlayer) {
            super.onReady(youTubePlayer)
            val videoId = "S0Q4gqBUs7c"
            youTubePlayer.loadVideo(videoId, 0F)
        }
    }
}