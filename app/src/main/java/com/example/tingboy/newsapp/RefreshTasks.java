package com.example.tingboy.newsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.tingboy.newsapp.model.DBHelper;
import com.example.tingboy.newsapp.model.DatabaseUtils;
import com.example.tingboy.newsapp.model.NewsItem;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Albert Ting on 7/25/2017.
 */

public class RefreshTasks {

    public static final String ACTION_REFRESH = "refresh";

    //Refreshes the articles. Completely removes them and replaces with new ones with every refresh
    public static void refreshArticles(Context context) {
        ArrayList<NewsItem> result = null;
        URL url = NetworkUtils.makeURL();

        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();

        try {
            DatabaseUtils.deleteAll(db);
            String json = NetworkUtils.getResponseFromHttpUrl(url);
            result = NetworkUtils.parseJSON(json);
            DatabaseUtils.bulkInsert(db, result);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        db.close();
    }
}