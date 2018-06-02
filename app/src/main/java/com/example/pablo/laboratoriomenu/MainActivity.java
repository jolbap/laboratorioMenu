package com.example.pablo.laboratoriomenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.example.pablo.laboratoriomenu.ItemMenu.ItemMenuStructure;
import com.example.pablo.laboratoriomenu.ItemMenu.MenuBaseAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private ListView LIST;
    private ArrayList<ItemMenuStructure> LISTINFO;
    private Context root;
    private MenuBaseAdapter ADAPTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        root = this;
        LISTINFO = new ArrayList<ItemMenuStructure>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //loadData();
        loadComponents();
    }

    private void loadComponents() {
        LIST = (ListView) this.findViewById(R.id.foodlist);
        EditText search = (EditText)this.findViewById(R.id.editText);
        loadData();
    }

    private void loadData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://181.114.179.122:7070/api/v1.0/food", new JsonHttpResponseHandler(){
           @Override
           public void onSuccess(int statusCode, Header[] headers, JSONObject response){
               try {
                    JSONArray listData = response.getJSONArray("info");


                    for (int i = 0; i < listData.length(); i++){
                        JSONObject obj = listData.getJSONObject(i);
                        String name = obj.getString("name");
                        int cantidad = obj.getInt("quantity");
                        String id = obj.getString("_id");
                        String urlimg = "http://181.114.179.122:7070/api/v1.0/foodimg/"+ id;
                        ItemMenuStructure item = new ItemMenuStructure(name,urlimg,cantidad,id);
                        LISTINFO.add(item);
                    }
                    ADAPTER = new MenuBaseAdapter(root, LISTINFO);
                    LIST.setAdapter(ADAPTER);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
