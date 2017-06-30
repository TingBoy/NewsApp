package com.example.tingboy.newsapp;

import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tingboy.newsapp.model.NewsItem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacksAr{
    public static final String TAG = "MainActivity";

    private NewsAdapter mNewsAdapter;
    private RecyclerView rV;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rV = (RecyclerView) findViewById(R.id.recyclerview_news);
        progress = (ProgressBar) findViewById(R.id.progressBar);

        rV.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadNewsData() {
        new NetworkTask().execute();
    }


    public class NetworkTask extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... params) {
            String result = null;
            URL url = NetworkUtils.makeURL();
            Log.d(TAG, "url: " + url.toString());
            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
                return jsonResponse;

            } catch(IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progress.setVisibility(View.GONE);
            if(s==null) {
                rV.setText("Sorry, no text was received");
            } else {
                rV.setText(s);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.menu, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_display) {
            mNewsAdapter.setNewsData(null);
            loadNewsData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
