package com.example.tingboy.newsapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by tingboy on 6/21/17.
 */

public class NetworkUtils {
    public static final String TAG = "NetworkUtils";

    public static final String base_url = "https://newsapi.org/v1/articles?source=the-next-web";
    public static final String sort_parameter = "latest";
    public static final String query_parameter = api.key();

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
}
