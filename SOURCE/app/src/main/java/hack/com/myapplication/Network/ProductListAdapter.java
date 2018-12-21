package hack.com.myapplication.Network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import hack.com.myapplication.Network.ObjNetwork.Data;
import hack.com.myapplication.R;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>{

    ArrayList<Data> arrayList;
    Context context;

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_rv_product_list_item, viewGroup, false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder productListViewHolder, int i) {
        Data data = arrayList.get(i);
        productListViewHolder.tvName.setText(data.getName());
        productListViewHolder.tvBasePrice.setText("" + data.getBasePrice());
        Picasso.get().load(data.getImage()).into(productListViewHolder.imgProductList);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ProductListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProductList;
        TextView tvBasePrice, tvName;
        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductList = itemView.findViewById(R.id.img_product_list);
            tvBasePrice = itemView.findViewById(R.id.tv_basePrice);
            tvName = itemView.findViewById(R.id.tv_name);

        }
    }
}
