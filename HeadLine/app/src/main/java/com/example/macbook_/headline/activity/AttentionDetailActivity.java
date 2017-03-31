package com.example.macbook_.headline.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.adapter.HomeDetailAdapter;
import com.example.macbook_.headline.bean.Home_Dtail;
import com.example.macbook_.headline.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class AttentionDetailActivity extends AppCompatActivity {

    private String title;
    private String url;
    private ListView lv;
    private TextView tv;
    private List<Home_Dtail> homeDtails=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_detail);
        lv = (ListView) findViewById(R.id.attentiondetail_lv);
        tv = (TextView) findViewById(R.id.attentiondetail_tv);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        tv.setText(title);
        getServerData();
    }
    private void getServerData() {

        new MyAsyncTask().execute(url);
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            try {
                HttpResponse response = client.execute(get);
                if (response.getStatusLine().getStatusCode() == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    String json = JsonUtils.jsonToString(inputStream);
                    return json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray jsonArray = new JSONObject(s).optJSONArray("data");
                Type type = new TypeToken<List<Home_Dtail>>() {
                }.getType();
                Gson gson = new Gson();
                homeDtails = gson.fromJson(jsonArray.toString(), type);
                lv.setAdapter(new HomeDetailAdapter(homeDtails, AttentionDetailActivity.this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
