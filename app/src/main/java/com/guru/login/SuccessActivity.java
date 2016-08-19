package com.guru.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SuccessActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<Banku> books;
    private ArrayAdapter<Banku> adapter;


    // String arrayURL [] ={"http://www.json-generator.com/api/json/get/ccLAsEcOSq?indent=2"."http://www.json-generator.com/api/json/get/ccLAsEcOSq?indent=2"."http://www.json-generator.com/api/json/get/ccLAsEcOSq?indent=2"};

    private final static String TAG = SuccessActivity.class.getSimpleName();
    private final static String url = "http://www.json-generator.com/api/json/get/ccLAsEcOSq?indent=2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        listview = (ListView) findViewById(R.id.listview);
        setListViewAdapter();
        getDataFromInternet();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                try {

                    String bku_name = books.get(position).getNamaBan().toString();
                    String lain = books.get(position).getKodeBan().toUpperCase().toString();
                    String url_to_detail = books.get(position).getImgUrl().toString();

                    Toast.makeText(SuccessActivity.this, bku_name + " is created by " + lain, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SuccessActivity.this, DetailActivity.class);
                    intent.putExtra("name", bku_name+" "+lain);
                    intent.putExtra("urltodetail", url_to_detail);
                    startActivity(intent);

                    //String project_location = list_data.get(position).get("project_location");


                } catch (Exception e) {

                    Log.d("Error Error", e.getMessage().toString());
                }


            }
        });
    }

    private void getDataFromInternet() {
        new GetJsonFromUrlTask(this, url).execute();
    }

    private void setListViewAdapter() {
        books = new ArrayList<Banku>();
        adapter = new CustomListViewAdapter(this, R.layout.item_listview, books);
        listview.setAdapter(adapter);
    }

    public void parseJsonResponse(String result) {
        Log.i(TAG, result);
        try {
            JSONObject json = new JSONObject(result);
            JSONArray jArray = new JSONArray(json.getString("book_array"));
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject jObject = jArray.getJSONObject(i);

                Banku book = new Banku();
                book.setNamaBan(jObject.getString("book_title"));
                book.setImgUrl(jObject.getString("image"));
                book.setKodeBan(jObject.getString("author"));
                books.add(book);
            }

            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
