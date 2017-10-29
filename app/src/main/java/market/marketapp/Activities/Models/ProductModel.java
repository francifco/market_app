package market.marketapp.Activities.Models;

/**
 * Created by FRANCI on 10/22/2017.
 */

public class ProductModel {
  public int Id;
  public String Description;
  public int Quantity;
  public String Code;
  public Double Price;

  public ProductModel(String description, int quantity){
    this.Description = description;
    this.Quantity = quantity;
  }
}
