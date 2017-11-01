package com.jacksonueda.movist.features.movieDetails

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.jacksonueda.movist.R
import com.jacksonueda.movist.base.BaseMvpActivity
import com.jacksonueda.movist.model.Movie
import com.jacksonueda.movist.utils.Utils
import jp.wasabeef.glide.transformations.MaskTransformation
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseMvpActivity<MovieDetailsContract.View, MovieDetailsContract.Presenter>(), MovieDetailsContract.View {

    override var mPresenter: MovieDetailsContract.Presenter = MovieDetailsPresenter()

    companion object {
        const val EXTRA_MOVIE: String = "EXTRA_MOVIE"
    }

    // ==========================================================================================
    // LIFECYCLE
    // ==========================================================================================

    override fun getLayout(): Int {
        return R.layout.activity_movie_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie: Movie? = intent.getSerializableExtra(EXTRA_MOVIE) as Movie

        setupToolBar()
        loadMovieDetails(movie)
    }

    // ==========================================================================================
    // SETUP
    // ==========================================================================================

    private fun setupToolBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    // ==========================================================================================
    // HELPER
    // ==========================================================================================

    fun loadMovieDetails(movie: Movie?) {
        movie.let {
            val coverUrl = movie?.backdropPath ?: movie?.posterPath

            movieTitle.text = movie?.title
            movieYear.text = Utils.getYear(movie?.releaseDate!!)
            movieRate.text = movie?.voteAverage.toString()
            movieOverview.text = movie?.overview


            val options = RequestOptions().centerCrop()
//                    .transform(MaskTransformation(R.drawable.gradient_background))


            Glide.with(this)
                    .load(getString(R.string.tmdb_background_url) + coverUrl)
                    .listener(
                            GlidePalette.with(getString(R.string.tmdb_background_url) + coverUrl)
//                                    .use(BitmapPalette.Profile.VIBRANT)
                                    .intoCallBack { palette ->
                                        if (palette?.vibrantSwatch?.rgb != null)
                                            viewMask.setColorFilter(palette?.vibrantSwatch?.rgb!!, android.graphics.PorterDuff.Mode.SRC_IN)
                                    }
                    )
                    .apply(options)
                    .into(movieCover)

//            Utils.loadImage(getString(R.string.tmdb_background_url) + coverUrl, this, movieCover)
            Utils.loadImage(getString(R.string.tmdb_poster_url) + movie?.posterPath, this, moviePoster)
        }

    }

    // ==========================================================================================
    // VIEW
    // ==========================================================================================

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayMovieDetails(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}