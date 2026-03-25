package com.zain.ordersystem;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zain.ordersystem.enums.FulfillmentType;
import com.zain.ordersystem.enums.PaymentMethod;
import com.zain.ordersystem.enums.Status;

@SpringBootApplication
public class OrderProcessingApplication implements CommandLineRunner {


	@Autowired
	private OrderProcessor orderProcessor;


	public static void main(String[] args) {
		SpringApplication.run(OrderProcessingApplication.class, args);
	}


	public OrderProcessingApplication() {
	}

	@Override
	public void run(String... args) {

		Item item1 = new Item("Laptop", 1200.00, 1);
		Item item2 = new Item("Mouse", 25.00, 2);

		ArrayList<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);

		Address deliveryAddress = new Address("123 Main St", "Friendswood", "TX", "77546");

		Order order1 = new Order("1", LocalDateTime.now(), items, PaymentMethod.CREDIT, "Zain Morshed", deliveryAddress, Status.CREATED, "zainmorshed@gmail.com", FulfillmentType.SHIPPING);


		orderProcessor.process(order1);

		System.out.println("Order Status: " + order1.getOrderStatus());
	}


}
