package com.example.tingboy.newsapp;

import android.net.Uri;
import android.util.Log;

import com.example.tingboy.newsapp.model.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tingboy on 6/21/17.
 */

public class NetworkUtils {
    public static final String TAG = "NetworkUtils";

    public static final String base_url = "https://newsapi.org/v1/articles?source=the-next-web";
    public static final String sort_parameter = "latest";
    public static final String query_parameter = api.key(); //pulls api key from separate class

    public static URL makeURL() {
        Uri uri = Uri.parse(base_url).buildUpon()
                .appendQueryParameter("sortBy", sort_parameter)
                .appendQueryParameter("apiKey", query_parameter).build();

        URL url = null;

        try {
            String urlString = uri.toString();
            Log.d(TAG, "Url: " + urlString);
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
    //parse JSON method
    public static ArrayList<NewsItem> parseJSON(String json) throws JSONException {
        ArrayList<NewsItem> result = new ArrayList<>();
        JSONObject articles = new JSONObject(json);
        JSONArray items = articles.getJSONArray("articles");

        for(int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String title = item.getString("title");
            String desc = item.getString("description");
            String date = item.getString("publishedAt");
            NewsItem nItem = new NewsItem(title, desc, date);
            result.add(nItem);
        }
        return result;
    }
}
