package market.marketapp.Activities.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import market.marketapp.Activities.Models.ProductModel;
import market.marketapp.R;

/**
 * Created by FRANCI on 10/22/2017.
 */

public class ProductsAdapter extends BaseAdapter {

  ArrayList<ProductModel> data;
  private Context context;
  private static LayoutInflater inflater = null;

  public ProductsAdapter(Context context,ArrayList<ProductModel> data) {
    this.context = context;
    this.data = data;
    inflater = (LayoutInflater) context
      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

  }

  @Override
  public int getCount() { // total number of elements in the list.
    return data.size();
  }

  @Override
  public Object getItem(int position) { // single item in the list.
    return data.get(position);
  }

  @Override
  public long getItemId(int position) { // index number.
    return position;
  }

  @Override
  public View getView(final int position, View view, ViewGroup parent) {

    if (view == null) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      view = inflater.inflate(R.layout.item_list_product, parent, false);
    }

    final ProductModel productModel = this.data.get(position);

    TextView tvDescription = (TextView) view.findViewById(R.id.product_name_text_view);
    tvDescription.setText(productModel.Description);

    TextView tvQuantity = (TextView) view.findViewById(R.id.product_quantity_text_view);
    tvQuantity.setText(Integer.toString(productModel.Quantity));

    final TextView btRemoveButton = (TextView) view.findViewById(R.id.remove_button);

    btRemoveButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int indexSelected = (int) getItemId(position);
        Remove(indexSelected);
      }
    });

    return view;
  }


  private void Remove(int index) {
    data.remove(index);
  }

}
