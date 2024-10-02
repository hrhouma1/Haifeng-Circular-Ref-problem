package com.eazybytes.cards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazybytes.cards.model.Customer;
import com.eazybytes.cards.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;   
    

    public List<Customer> getAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }

    public String saveCustomer(Customer customer){
        customerRepository.save(customer);
        return "saved";
    }

}
