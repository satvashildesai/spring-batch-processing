package com.example.batch.model;

public class Product {
	private int productId;
	private String title;
	private String description;
	private double price;
	private double discount;
	private double discountedPrice;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int productId, String title, String description, double price, double discount,
			double discountedPrice) {
		super();
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.discountedPrice = discountedPrice;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", title=" + title + ", description=" + description + ", price="
				+ price + ", discount=" + discount + ", discountedPrice=" + discountedPrice + "]";
	}
}
