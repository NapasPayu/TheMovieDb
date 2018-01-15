package com.napas.themoviedb.ui.main;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.napas.themoviedb.BuildConfig;
import com.napas.themoviedb.R;
import com.napas.themoviedb.data.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends BaseQuickAdapter<Movie, BaseViewHolder> {

    private Context mContext;

    public MovieListAdapter(Context context, List<Movie> movies) {
        super(R.layout.item_movie, movies);
        setHasStableIds(true);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Movie movie) {
        ImageView ivPoster = helper.getView(R.id.iv_poster);
        Picasso.with(mContext).load(BuildConfig.POSTER_URL + movie.getPosterPath()).fit().centerCrop().into(ivPoster);
    }
}
