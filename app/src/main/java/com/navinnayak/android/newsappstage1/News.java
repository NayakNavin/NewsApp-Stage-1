package com.navinnayak.android.newsappstage1;

/**
 * An {@link News} object contains information related to a single earthquake.
 */
public class News {

    /**
     * Title of the news
     */
    private String mTitle;

    /**
     * Website URL of the news
     */
    private String mUrl;

    /**
     * Publication date of the news
     */
    private String mDateTime;

    /**
     * Author(s) of the news
     */
    private String mAuthor;

    /**
     * Section to which the news belongs
     */
    private String mSection;

    /**
     * Constructs a new {@link News} object.
     *
     * @param title    is the title of the news
     * @param section  is the section to which the news belongs
     * @param dateTime is the date in which the news has been published
     * @param url      is the link to the web page of the news
     * @param author   is the author of the news
     */

    public News(String title, String section, String dateTime, String url, String author) {
        mTitle = title;
        mSection = section;
        mDateTime = dateTime;
        mUrl = url;
        mAuthor = author;
    }

    /**
     * Returns the title of the news.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the section of the news.
     */
    public String getSection() {
        return mSection;
    }

    /**
     * Returns the date and time of the news.
     */
    public String getDateTime() {
        return mDateTime;
    }

    /**
     * Returns the url of the news.
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Returns the author of the news.
     */
    public String getAuthor() {
        return mAuthor;
    }
}