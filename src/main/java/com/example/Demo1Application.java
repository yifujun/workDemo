package com.example;
import com.example.thread.SendMailTaskThread;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Demo1Application extends SendMailTaskThread {
    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }
    
}
