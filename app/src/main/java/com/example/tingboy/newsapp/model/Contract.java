package com.example.tingboy.newsapp.model;

import android.provider.BaseColumns;

/**
 * Created by Albert Ting on 7/25/2017.
 */
//Contract for storing data from news stories
public class Contract {

    public static class TABLE_ARTICLES implements BaseColumns {
        public static final String TABLE_NAME = "articles";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PUBLISHED_DATE = "date";
        public static final String COLUMN_NAME_DESCRIPTION = "desc";
        public static final String COLUMN_NAME_IMGURL = "imgurl";
        public static final String COLUMN_NAME_URL = "url";
    }
}

