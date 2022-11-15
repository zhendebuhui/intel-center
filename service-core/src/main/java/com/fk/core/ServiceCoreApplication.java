package com.fk.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author Yjw
 * 2022/11/10 23:51
 */
@SpringBootApplication
public class ServiceCoreApplication {
    public static void main(String[] args) {
        try{
            SpringApplication.run(ServiceCoreApplication.class,args);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
