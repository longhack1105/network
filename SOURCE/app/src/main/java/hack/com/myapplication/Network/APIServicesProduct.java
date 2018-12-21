package hack.com.myapplication.Network;

import hack.com.myapplication.Network.ObjNetwork.ProductList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServicesProduct {
    @GET("/api/json/get/cepQkfspDS?indent=2")
    Call<ProductList> callProductList();
}
