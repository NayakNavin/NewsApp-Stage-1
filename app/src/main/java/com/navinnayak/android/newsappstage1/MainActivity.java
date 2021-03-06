package com.navinnayak.android.newsappstage1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, android.support.v4.app.LoaderManager.LoaderCallbacks<List<News>> {

    /**
     * Adapter for the list of news
     */
    private NewsAdapter mAdapter;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * URL for news data from the Guardian
     */
    private static String REQUEST_URL =
            "http://content.guardianapis.com/search?show-tags=contributor&api-key=a6ce801a-77a8-44a1-a14d-7c3eacf86d30";

    private static int LOADER_ID = 0;
    SwipeRefreshLayout swipe;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    private ListView newsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        // Find a reference to the {@link ListView} in the layout
        newsListView = findViewById(R.id.list);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mEmptyStateTextView.setTextAppearance(R.style.TextAppearance_AppCompat_Headline);
        }
        newsListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of news as input
        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news.
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current news that was clicked on
                News currentNews = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getUrl());

                // Create a new intent to view the news URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    // onCreateLoader instantiates and returns a new Loader for the given ID
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        swipe.setRefreshing(false);

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            this.showResults(news);
        } else {
            this.hideResults();
        }
    }

    private void showResults(List<News> newsList) {
        mAdapter.clear();
        newsListView.setVisibility(View.VISIBLE);
        mEmptyStateTextView.setVisibility(View.GONE);
        mAdapter.setNotifyOnChange(false);
        mAdapter.setNotifyOnChange(true);
        mAdapter.addAll(newsList);
    }

    private void hideResults() {
        newsListView.setVisibility(View.GONE);
        mEmptyStateTextView.setVisibility(View.VISIBLE);
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            mEmptyStateTextView.setText(R.string.no_news);
            Log.e(LOG_TAG, "no news");

        } else {
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            Log.e(LOG_TAG, "no internet");
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }
}