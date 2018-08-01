package guc.supermarket.products;

public abstract class GroceryProduct {
	private String name;
	private double price;
	private double discount;
	
	public GroceryProduct(String name, double price, double discount){
		this.name=name;
		this.price=price;
		this.discount=discount;
	}
	
	public abstract boolean refrigerate();
	
	public final double getActualPrice()
	{
		return price-(price*discount/100);
	}
	public String fullInfo(){
		return(
		"name: "+name+"\n"+
		"Price: "+price+"\n"+
		"Discount: "+discount+" %");
	}
	public String toString(){
		return String.format("%s ($%.2f)", name, getActualPrice());
	}
	
	//-------------------getter & setter methods ------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
