package com.navinnayak.android.newsappstage1;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Loads a list of news by using an AsyncTask to perform the
 * network request to the given URL.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     */
    public NewsLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of news.
        List<News> newsList = QueryUtils.fetchNewsData(mUrl);
        return newsList;
    }
}