package com.eazybytes.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.cards.model.Customer;
import com.eazybytes.cards.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
	@Operation(summary = "get all customer")
	@GetMapping("/Allcustomers")
	public List<Customer> getAllCustomers(){
        List<Customer> allCustomers = customerService.getAllCustomers();
        return allCustomers;
	}

	@Operation(summary = "create a new customer")
	@PostMapping("/newCustomer")
	public String newCustomer(@RequestBody Customer customer){
		return customerService.saveCustomer(customer);
	}
}
