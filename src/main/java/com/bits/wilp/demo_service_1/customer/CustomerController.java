package com.bits.wilp.demo_service_1.customer;

import com.bits.wilp.demo_service_1.notify.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    KafkaProducer kafkaProducer;

    public CustomerController(CustomerRepository customerRepository, KafkaProducer kafkaProducer) {
        this.customerRepository = customerRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/customer")
    public Customer insertCustomer(@RequestBody Customer customer) {
        kafkaProducer.notifyCustomer(customer.getId());
        return customerRepository.save(customer);
    }

    @GetMapping(value = "/customer/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerRepository.findById(id).orElseThrow();
    }

}
