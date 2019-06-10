package com.dicoding.iwakuproduct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<Product> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list.addAll(ProductData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListProductAdapter listProductAdapter = new ListProductAdapter(this);
        listProductAdapter.setListProduct(list);
        rvCategory.setAdapter(listProductAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v){
                showSelectedProduct(list.get(position));
            }
        });
    }

    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridProductAdapter gridProductAdapter = new GridProductAdapter(this);
        gridProductAdapter.setListProduct(list);
        rvCategory.setAdapter(gridProductAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v){
                showSelectedProduct(list.get(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_list:
                showRecyclerList();
                break;

            case R.id.action_grid:
                showRecyclerGrid();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showSelectedProduct(Product product){

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        intent.putExtra("name", product.getName());
        intent.putExtra("remark", product.getRemarks());
        intent.putExtra("stock", "Stock tersisa "+product.getStocks());
        intent.putExtra("condition", product.getCondition());
        intent.putExtra("size", product.getSize());
        intent.putExtra("weight", product.getWeight());
        intent.putExtra(DetailActivity.PHOTO, product.getPhoto());

        startActivity(intent);

        //Toast.makeText(this, "Kamu memilih "+product.getName(), Toast.LENGTH_SHORT).show();
    }
}
