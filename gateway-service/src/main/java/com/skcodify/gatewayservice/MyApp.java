package com.skcodify.gatewayservice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyApp {

    public static void main(String args[]){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedShekhar = encoder.encode("shekhar");
        String encodedAdmin = encoder.encode("admin");

        System.out.println("Shekhar: " + encodedShekhar);
        System.out.println("Admin: " + encodedAdmin);
    }
}
