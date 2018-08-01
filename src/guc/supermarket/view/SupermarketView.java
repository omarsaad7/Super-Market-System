package guc.supermarket.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import guc.supermarket.exceptions.CannotBuyException;
import guc.supermarket.people.Customer;
import guc.supermarket.products.GroceryProduct;
import guc.supermarket.Supermarket;

@SuppressWarnings("serial")
public class SupermarketView extends JFrame implements ActionListener {

	private Supermarket supermarket;
	private ArrayList<JButton> btnsProduct;
	private Customer customer;

	private JPanel pnlProducts;
	private JTextArea txtCart;

	public static void main(String[] args) {
		new SupermarketView();
	}

	public SupermarketView() {

		// set the windows title
		setTitle("Supermarket");
		// change the default close operation of the JFrame to exit the
		// application instead of hiding the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// set the location and size of the JFrame
		setBounds(50, 50, 800, 600);

		// create a panel to hold the products buy buttons
		pnlProducts = new JPanel();
		// set it to use the GridLayout with 3 columns in width
		pnlProducts.setLayout(new GridLayout(0, 3));
		// add it in the center of the JFrame
		add(pnlProducts, BorderLayout.CENTER);

		// create a text area to hold the text of the cart
		txtCart = new JTextArea();
		// force it to have a width of 200 and the same height of the JFrame
		txtCart.setPreferredSize(new Dimension(200, getHeight()));
		// prevent its contents from being edited
		txtCart.setEditable(false);
		// use a monospaced font to make it look cooler
		txtCart.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		// add it to the right of the JFrame
		add(txtCart, BorderLayout.EAST);

		// create a new instance of Supermarket
		supermarket = new Supermarket();

		// initialize the customer
		customer = supermarket.getCustomers().get(0);

		// initialize the ArrayList of product buttons
		btnsProduct = new ArrayList<>();

		for (final GroceryProduct product : supermarket.getProducts()) {
			// create a JButton for each product in the supermarket
			JButton btnProduct = new JButton();
			// set its text to the product's info
			btnProduct.setText(product.toString());
			// add its ActionListener
			btnProduct.addActionListener(this);
			// add it to the products buy buttons panel
			this.addProduct(btnProduct);

			// and also add it to the ArrayList for later use
			btnsProduct.add(btnProduct);
		}

		// initialize the cart's text by setting its total to zero
		updateCart("", 0);

		// make the JFrame visible
		this.setVisible(true);
	}

	// adds the product JButton to the products buy buttons panel
	public void addProduct(JButton product) {
		pnlProducts.add(product);
	}

	// updates the cart's text with the list of products in the cart and the new total
	public void updateCart(String cartDetails, double total) {
		String cart = "";
		cart += "Cart:\n";
		cart += "'''''\n";
		cart += cartDetails;
		if (cartDetails != "") {
			cart += "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
		}
		cart += String.format("\nTotal: %21s", String.format("$%.2f", total));
		txtCart.setText(cart);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get the JButton that was clicked
		JButton btnProduct = (JButton) e.getSource();
		// get its index within the ArrayList of buttons
		int productIndex = btnsProduct.indexOf(btnProduct);
		// get the corresponding product from the supermarket
		GroceryProduct product = supermarket.getProducts().get(productIndex);
		// invoke the buy method on it
		try {
			customer.buy(product);
		} catch (CannotBuyException e1) {
			e1.printStackTrace();
		}

		// set the list along with the total to the updateCart method
		this.updateCart(customer.getCart().toString(), customer.getCart()
				.getTotal());
	}
}
