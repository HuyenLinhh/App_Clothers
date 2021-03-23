package com.example.appthuongmai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appthuongmai.R;
import com.example.appthuongmai.adapter.ProductByCategoryIdAdapter;
import com.example.appthuongmai.connect.CheckConnection;
import com.example.appthuongmai.model.Category;
import com.example.appthuongmai.model.Product;
import com.example.appthuongmai.util.Server;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private ProductByCategoryIdAdapter productByCategoryIdAdapter;
    private List<Product> productList;
    private Toolbar toolbarProduct;
    private RecyclerView rcvProduct;
//    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mapping();

        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            intActionBar();
            init();
        }else{
            CheckConnection.ShowToast_short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }

    private void init() {
        Intent intent = getIntent();
        Category cate = (Category) intent.getSerializableExtra("category");
        getAllProductByCategoryId(cate.getId());
    }

    private void getAllProductByCategoryId(int id) {
        productList = new ArrayList<>();
        productByCategoryIdAdapter = new ProductByCategoryIdAdapter(this, R.layout.line_product, productList);
        rcvProduct.setAdapter(productByCategoryIdAdapter);
        rcvProduct.setHasFixedSize(true);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, Server.URL_GET_PRODUCT_BY_CATEGORY_ID+"?categoryid="+id, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject obj;
                            for (int i = 0; i < response.length(); i++) {
                                obj = response.getJSONObject(i);
                                productList.add(new Product(obj.getInt("id"), obj.getString("name"),
                                        obj.getInt("price"), obj.getString("avatar"),
                                        obj.getString("description"), obj.getInt("categoryid")));
                            }
                            productByCategoryIdAdapter.notifyDataSetChanged();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(arrayRequest);
    }

    private void intActionBar() {
        setSupportActionBar(toolbarProduct);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarProduct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        toolbarProduct = findViewById(R.id.toolbarProduct);
        rcvProduct = findViewById(R.id.rcvProduct);
    }
}
