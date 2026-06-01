package com.example.bai2;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/auth/register")
    public String register() {
        return "Register endpoint - public";
    }

    @PostMapping("/auth/login")
    public String login() {
        return "Login endpoint - public";
    }

    @GetMapping("/customers")
    public String getCustomers() {
        return "Customer list - requires authentication";
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable String id) {
        return "Delete customer " + id + " - requires authentication";
    }
}
