package com.example.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.example.batch.model.Product;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {
	Logger log = LoggerFactory.getLogger(CustomItemProcessor.class);

	@Override
	public Product process(Product item) throws Exception {
//		Process source data and store it into the database
//		log.info(Integer.toString(item.getProductId()));
		double discount = item.getDiscount();
		double originalPrice = item.getPrice();
		double discountedPrice = originalPrice - (originalPrice * (discount / 100));
		item.setDiscountedPrice(discountedPrice);
		return item;
	}
}
