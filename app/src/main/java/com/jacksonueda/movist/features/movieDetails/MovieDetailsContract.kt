package com.jacksonueda.movist.features.movieDetails

import com.jacksonueda.movist.base.BaseMvpPresenter
import com.jacksonueda.movist.base.BaseMvpView
import com.jacksonueda.movist.model.Movie

/**
 * Created by Jackson on 28/10/17.
 */

object MovieDetailsContract {

    interface View : BaseMvpView {
        fun showLoading()
        fun dismissLoading()
        fun displayMovieDetails(movie: Movie)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun getMovie(movieId: Long)
    }

}