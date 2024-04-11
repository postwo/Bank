package com.example.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
       ConfigurableApplicationContext context =  SpringApplication.run(BankApplication.class, args);

//       String[] iocnames = context.getBeanDefinitionNames();
//       for (String name : iocnames){
//           System.out.println(name);
//       }
    }

}
