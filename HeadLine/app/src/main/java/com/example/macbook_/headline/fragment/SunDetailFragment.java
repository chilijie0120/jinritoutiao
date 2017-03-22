package com.example.macbook_.headline.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.adapter.SunDetailFragmentAdapter;
import com.example.macbook_.headline.bean.Sun;
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
import xlistview.bawei.com.xlistviewlibrary.XListView;

/**
 * Created by MacBook- on 2017/3/13.
 */
public class SunDetailFragment extends Fragment {

    private XListView sun_xlv;
    private List<Sun> sunDtails = new ArrayList<>();
    private String[] jsonu = new String[]{
           "http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-20.html",
            "http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/10-10.html",
            "http://c.3g.163.com/nc/video/list/V9LG4E6VR/n/10-10.html",
            "http://c.3g.163.com/nc/video/list/00850FRB/n/10-10.html"
    };
    private int id;
    private String jsonUrl;
    private String name;

    public static SunDetailFragment getFragment(int id,String name) {
        SunDetailFragment fragment = new SunDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("name",name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sundetailfragment, null);
        sun_xlv = (XListView) view.findViewById(R.id.sun_xlv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        id = getArguments().getInt("id");
        name = getArguments().getString("name");
        getServerData();
    }

    private void getServerData() {
        jsonUrl = jsonu[id];
        new MyAsyncTask().execute(jsonUrl);
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
                    JSONArray jsonArray = new JSONObject(s).optJSONArray(name);
                    Type type = new TypeToken<List<Sun>>() {}.getType();
                    Gson gson = new Gson();
                    sunDtails = gson.fromJson(jsonArray.toString(), type);
                    sun_xlv.setAdapter(new SunDetailFragmentAdapter(sunDtails, getActivity()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
