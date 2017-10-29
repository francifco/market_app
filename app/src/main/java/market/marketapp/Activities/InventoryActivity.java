package market.marketapp.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import market.marketapp.Activities.Adapters.ProductsAdapter;
import market.marketapp.Activities.Models.ProductModel;
import market.marketapp.R;

/**
 * Created by FRANCI on 10/22/2017.
 */

public class InventoryActivity extends AppCompatActivity {

  ListView listview;
  ArrayList<ProductModel> listProduct;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inventory);

    listview = (ListView) findViewById(R.id.products_list_view);

    listProduct = getProducts();

    listview.setAdapter(new ProductsAdapter(this, listProduct));


  }

  private ArrayList<ProductModel> getProducts() {
    ArrayList<ProductModel> list = new ArrayList<ProductModel>();

    for (int i = 0; i < 10; i++) {
      ProductModel product = new ProductModel("salami",5);
      list.add(product);
    }

    return list;
  }


}
