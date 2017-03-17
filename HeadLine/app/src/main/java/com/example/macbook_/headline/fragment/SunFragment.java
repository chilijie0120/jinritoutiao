package com.example.macbook_.headline.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.adapter.SunFragmentAdapter;
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
 * Created by MacBook- on 2017/3/11.
 */
public class SunFragment extends Fragment {
    private XListView sun_xlv;
    private List<Sun> list=new ArrayList<>();
    private String jsonUrl="http://ic.snssdk.com/2/article/v25/stream/?category=video&count=20&min_behot_time=1455521349&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522107&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sunfragment,null);
        initView(view);

        return view;
    }
    private void initView(View view) {

        sun_xlv = (XListView) view.findViewById(R.id.sun_xlv);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getServerData();
    }
    private void getServerData() {
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
                JSONArray jsonArray = new JSONObject(s).optJSONArray("data");
                Type type = new TypeToken<List<Sun>>() {}.getType();
                Gson gson = new Gson();
                list = gson.fromJson(jsonArray.toString(), type);
                sun_xlv.setAdapter(new SunFragmentAdapter(list, getActivity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
