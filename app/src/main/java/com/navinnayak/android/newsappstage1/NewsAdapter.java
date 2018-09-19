package com.navinnayak.android.newsappstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * An {@link NewsAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link News} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param news    is the list of news, which is the data source of the adapter
     */
    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    /**
     * Returns a list item view that displays information about the news at the given position
     * in the list of news.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }

        // Find the news at the given position in the list of news
        News currentNews = getItem(position);

        // Find the TextView with view ID article_title
        // Get the title string from the news object,
        // Display the title of the current news in that TextView
        TextView newsTitleTextView = listItemView.findViewById(R.id.article_title);
        String title = currentNews.getTitle();
        newsTitleTextView.setText(title);

        // Find the TextView with view ID article_section
        // Get the section string from the news object,
        // Display the section of the current news in that TextView
        TextView newsSectionTextView = listItemView.findViewById(R.id.article_section);
        String section = currentNews.getSection();
        newsSectionTextView.setText(section);

        // Find the TextView with view ID article_date
        // Get the section string from the news object,
        // Display the date and time of the current news in that TextView
        TextView newsDateTimeTextView = listItemView.findViewById(R.id.article_date);
        String dateTime = currentNews.getDateTime();
        newsDateTimeTextView.setText(dateTime);

        // Find the TextView with view ID article_authors
        // Get the author string from the news object,
        // Display the author of the current news in that TextView
        TextView newsAuthorTextView = listItemView.findViewById(R.id.article_authors);
        String author = currentNews.getAuthor();
        newsAuthorTextView.setText(author);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}