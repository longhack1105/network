package hack.com.myapplication.Network;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import hack.com.myapplication.Network.ObjNetwork.Data;
import hack.com.myapplication.Network.ObjNetwork.ProductList;
import hack.com.myapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductListActivity extends AppCompatActivity {

    ArrayList<Data> arrayList = new ArrayList<>();
    ProductListAdapter adapter = new ProductListAdapter();
    RecyclerView rvProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        init();
    }

    void init() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.json-generator.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(APIServicesProduct.class).callProductList().enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                if (response.isSuccessful()) {
                    ProductList productList = response.body();
                    if (productList.getStatus() == 1) {
                        //code here
                        rvProductList = findViewById(R.id.rv_product_list);
                        for (int i=0; i<productList.getData().size(); i++){
                            Data data = new Data();
                            data.setCode(Integer.parseInt(productList.getData().get(i).getCode().toString()));
                            data.setWeight(Integer.parseInt(productList.getData().get(i).getWeight().toString()));
                            data.setBasePrice(Integer.parseInt(productList.getData().get(i).getBasePrice().toString()));
                            data.setConversionValue(Integer.parseInt(productList.getData().get(i).getConversionValue().toString()));
                            data.setId(Integer.parseInt(productList.getData().get(i).getId().toString()));
                            data.setCategoryName(productList.getData().get(i).getCategoryName());
                            data.setImage(productList.getData().get(i).getImage());
                            data.setCreatedDate(productList.getData().get(i).getCreatedDate());
                            data.setName(productList.getData().get(i).getName());

                            arrayList.add(data);

                        }
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductListActivity.this, 2);
                        adapter.arrayList = arrayList;
                        adapter.context = ProductListActivity.this;
                        rvProductList.setAdapter(adapter);
                        rvProductList.setLayoutManager(gridLayoutManager);

                    } else {
                        Toast.makeText(ProductListActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {

            }
        });
    }
}
