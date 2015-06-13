package com.kawakawaplanning.rssreader.Async;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.kawakawaplanning.rssreader.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by KP on 15/05/04.
 */
public class GetTitleTask extends AsyncTask {
    JSONArray items;
    JSONObject titleGet;
    String searchUrl;

    static int i = 0;

    ArrayAdapter<Item> adapter;

    public GetTitleTask(String url, ArrayAdapter<Item> adapter){
        searchUrl = url;
        this.adapter= adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(Object... params) {
        String get = null;
        try {
            URLConnection connection = new URL("https://ajax.googleapis.com/ajax/services/feed/load?v=1.0&q=" + searchUrl + "&num=30").openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            get = reader.readLine();
        }catch (IOException e) {
        }
        return get;
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        try {
            JSONObject json = new JSONObject(o.toString());
            titleGet = json.getJSONObject("responseData").getJSONObject("feed");
            titleGet.getString("title");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
