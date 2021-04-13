package org.example.volleyforjavacrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.example.volleyforjavacrud.apps.InsertActivity;
import org.example.volleyforjavacrud.design.CapitalAdapter;
import org.example.volleyforjavacrud.entity.Country;
import org.example.volleyforjavacrud.volleyapi.Constants;
import org.example.volleyforjavacrud.volleyapi.MySingleton;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CapitalAdapter capitalAdapter;
    private List<Country> countryList;
    private Country country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.country_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getCountryList();
    }

    private void getCountryList(){
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.BASE_LIST_URL, null,
                response -> {

                    try {

                        countryList = new ArrayList<>();
                        JSONArray jsonArray = response.getJSONArray(Constants.KEY_JSON_ARRAY_ROOT_ITEM);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            country = new Country();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            country.setCountryName(jsonObject.getString(Constants.KEY_COUNTRY_NAME));
                            country.setCapitalName(jsonObject.getString(Constants.KEY_CAPITAL_NAME));
                            countryList.add(country);

                        }

                        capitalAdapter = new CapitalAdapter(MainActivity.this, countryList);
                        capitalAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(capitalAdapter);

                    } catch (Exception e) {
                        e.getMessage();
                    }
                },
                error -> Toast.makeText(MainActivity.this, error.getMessage(),
                        Toast.LENGTH_LONG).show());

        MySingleton.getInstance(this).addToRequestQueue(objectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.setting_menu:
                Toast.makeText(MainActivity.this, "Setting Menu", Toast.LENGTH_SHORT).show();
            break;
            case R.id.insert_menu:
                startActivity(new Intent(MainActivity.this , InsertActivity.class));
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}