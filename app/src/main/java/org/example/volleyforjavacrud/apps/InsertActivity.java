package org.example.volleyforjavacrud.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import org.example.volleyforjavacrud.R;
import org.example.volleyforjavacrud.volleyapi.Constants;
import org.example.volleyforjavacrud.volleyapi.MySingleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InsertActivity extends AppCompatActivity {

    private TextInputEditText countryName , capitalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        countryName = findViewById(R.id.countryNameEditText);
        capitalName = findViewById(R.id.capitalNameEditText);
    }

    private void insertRecords(){

        String nameCountry = Objects.requireNonNull(countryName.getText()).toString().trim();
        String nameCapital = Objects.requireNonNull(capitalName.getText()).toString().trim();

        if(nameCountry.isEmpty() && nameCapital.isEmpty()){
            Toast.makeText(InsertActivity.this, "Enter required information", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.BASE_INSERT_URL,

                response -> Toast.makeText(InsertActivity.this, "Insert Records Successfully..."
                        , Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(InsertActivity.this, "Error Record Insert..."
                        , Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() {

                Map<String , String> stringMap = new HashMap<>();
                stringMap.put(Constants.KEY_COUNTRY_NAME , nameCountry);
                stringMap.put(Constants.KEY_CAPITAL_NAME , nameCapital);
                return stringMap;
            }

        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        countryName.setText("");
        capitalName.setText("");
    }

    public void AddRecords(View view) {
        insertRecords();
    }
}