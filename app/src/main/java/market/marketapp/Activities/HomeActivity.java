package market.marketapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import market.marketapp.R;

public class HomeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);


    final Button buyButton = (Button) findViewById(R.id.buy_button);
    final Button inventoryButton = (Button) findViewById(R.id.inventory_button);


    inventoryButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        GoToInventory();
      }
    });

  }


  private void GoToInventory(){
    Intent intent = new Intent(getBaseContext(), InventoryActivity.class);
    startActivity(intent);
  }

}
