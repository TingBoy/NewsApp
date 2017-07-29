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

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private RecyclerView rV;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rV = (RecyclerView) findViewById(R.id.recyclerview_news);
        progress = (ProgressBar) findViewById(R.id.progressBar);

        rV.setLayoutManager(new LinearLayoutManager(this));

        NewsTask task = new NewsTask();
        task.execute();
    }


    public class NewsTask extends AsyncTask<URL, Void, ArrayList<NewsItem>> {


        @Override
        protected ArrayList<NewsItem> doInBackground(URL... params) {
            ArrayList<NewsItem> result = null;
            URL url = NetworkUtils.makeURL();
            Log.d(TAG, "url: " + url.toString());
            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
                result = NetworkUtils.parseJSON(jsonResponse);

            } catch(IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<NewsItem> data) {
            super.onPostExecute(data);

            progress.setVisibility(View.GONE);
            if(data==null) {
                NewsAdapter adapter = new NewsAdapter(data, new NewsAdapter.ItemClickListener() {

                    @Override
                    public void onItemClick(int itemIndex) {
                        Uri uri = Uri.parse(data.get(itemIndex).getUrl();)
                    }
                    "Sorry, no text was received")
                };
            } else {
                rV.setText(data);
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
