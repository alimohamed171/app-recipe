package com.example.testrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class DetailFragment : Fragment() {
    lateinit var videoid: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image: ImageView = view.findViewById(R.id.imgFood)
        val title: TextView = view.findViewById(R.id.textTitle)
        val details: TextView = view.findViewById(R.id.textDetails)
        val video: YouTubePlayerView = view.findViewById(R.id.youtube_player_view)

        val args: DetailFragmentArgs by navArgs()
        Glide.with(this.requireActivity())
            .load(args.image)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loadin_image)
                    .error(R.drawable.broken_image))
            .into(image)
        title.text = args.name
        details.text = args.details
        videoid = args.video

        controlYouTubePlayer(view, videoid)
    }

    private fun onCheckedFavouriteButton() {
//        checkBox.setOnCheckedChangeListener(checkBox, isChecked ->
//        if (isChecked)
//            TODO()
//        else
//            TODO()
//        )
    }

    private fun controlYouTubePlayer(view: View, videoid: String) {
        // add YouTubePlayerView as a lifecycle observer of its parent
        val youTubePlayerView: YouTubePlayerView = view.findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)

        // open video by user
        youTubePlayerView.addYouTubePlayerListener(YouTubePlayerListener(videoid))
    }

    class YouTubePlayerListener(val videoid: String): AbstractYouTubePlayerListener(){
        val id = getYouTubeId(videoid)
        override fun onReady(youTubePlayer: YouTubePlayer) {
            super.onReady(youTubePlayer)
            val videoId = id
            youTubePlayer.loadVideo(videoId, 0F)
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
}