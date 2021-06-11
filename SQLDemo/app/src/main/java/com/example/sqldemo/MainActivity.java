package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_activeCustomer;
    ListView lv_customerList;
    ArrayAdapter customerArrayAdapter;
    List<CustomerModel> everyone;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewall);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        sw_activeCustomer = findViewById(R.id.sw_active);
        lv_customerList = findViewById(R.id.lv_customerList);
        dataBaseHelper = new DataBaseHelper((MainActivity.this));


        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this,android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_customerList.setAdapter(customerArrayAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(-1,et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()));
                }catch (Exception e){
                    customerModel = new CustomerModel(-1, "error", 0);
                }
                boolean success = dataBaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "Success = "+success,Toast.LENGTH_SHORT).show();
                customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this,android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                lv_customerList.setAdapter(customerArrayAdapter);
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this,android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                lv_customerList.setAdapter(customerArrayAdapter);

            }
        });
    }
}