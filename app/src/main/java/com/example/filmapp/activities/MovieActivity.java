package com.example.filmapp.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.filmapp.R;

import androidx.appcompat.app.AppCompatActivity;

import com.example.filmapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieActivity extends AppCompatActivity {

    private ListView movieListView;
    private List<String> movieTitles = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieListView = findViewById(R.id.movieListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, movieTitles);
        movieListView.setAdapter(adapter);

        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedMovie = movieTitles.get(position);
                Toast.makeText(MovieActivity.this, "Você selecionou: " + selectedMovie, Toast.LENGTH_SHORT).show();
            }
        });

        new FetchMoviesTask().execute();
    }

    private class FetchMoviesTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/movie/popular?api_key=b7165c42722d26bcc9a3377dd3cb0ffd")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray results = json.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject movie = results.getJSONObject(i);
                        String title = movie.getString("title");
                        movieTitles.add(title);
                    }

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MovieActivity.this, "Falha ao obter filmes.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
