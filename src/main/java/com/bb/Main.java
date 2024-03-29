package com.bb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main  {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping
    public List<Customer> addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);

        return customerRepository.findAll();
    }

    @DeleteMapping("{customerId}")
    public List<Customer> deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);

        return customerRepository.findAll();
    }

    @PutMapping("{customerId}")
    public List<Customer> updateCustomer(@PathVariable("customerId") Integer id,
                               @RequestBody Customer customer) {
        customer.setId(id);
        customerRepository.save(customer);

        return customerRepository.findAll();
    }

}
