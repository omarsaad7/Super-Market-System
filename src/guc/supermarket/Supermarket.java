package guc.supermarket;

import java.util.ArrayList;
import java.util.Random;

import guc.supermarket.cart.Cart;
import guc.supermarket.people.Customer;
import guc.supermarket.products.Beverage;
import guc.supermarket.products.DairyProduct;
import guc.supermarket.products.Fat;
import guc.supermarket.products.GroceryProduct;
import guc.supermarket.products.SugarLevel;

public class Supermarket {

	private ArrayList<GroceryProduct> products;
	private ArrayList<Customer> customers;

	// Supermarket Class
	public Supermarket() {

		products = new ArrayList<GroceryProduct>();
		customers = new ArrayList<Customer>();

		// create a customer
		Customer c = new Customer("Adam", new Cart(), Fat.FULLCREAM,
				SugarLevel.ADDED_SUGAR);

		// add the customer to the list of customers
		customers.add(c);

		// generate some products
		for (int i = 1; i <= 10; i++) {
			// generate a random product
			GroceryProduct product = someRandomProduct(i);

			// add the product to the supermarket's list of products
			products.add(product);
		}

	}

	/**
	 * Returns a randomly generated {@link GroceryProduct} to be added to the
	 * {@link Supermarket}'s list of products
	 * 
	 * @param index
	 *            a number indicating the index of the product being generated,
	 *            to be used in its name
	 * @return the generated {@link GroceryProduct}
	 */
	private GroceryProduct someRandomProduct(int index) {
		char productType = new char[] { 'D', 'B' }[new Random().nextInt(2)];
		double price = new Random().nextDouble() * 20;
		int discount = new Random().nextInt(6) * 10;

		switch (productType) {
		case 'D':
			Fat fatType = customers.get(0).getPreferableFatLevel();
			return new DairyProduct("Dairy Product " + index, price, discount,
					fatType);
		case 'B':
			SugarLevel sugar = customers.get(0).getPreferableSugarLevel();
			return new Beverage("Beverage " + index, price, discount, sugar);
		}

		return null;
	}

	public void addCustomer(Customer c) {

		customers.add(c);

	}

	public void addProduct(GroceryProduct c) {

		products.add(c);

	}

	public ArrayList<GroceryProduct> getProducts() {

		return products;

	}

	public ArrayList<Customer> getCustomers() {

		return customers;

	}

}
