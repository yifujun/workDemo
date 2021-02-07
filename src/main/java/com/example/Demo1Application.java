package com.example;
import com.example.thread.SendMailTaskThread;
import lombok.Data;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application extends SendMailTaskThread {
    public static void main(String[] args) {
        String str ="投保人%s在我司留存的身份证件已过期、即将过期或未提供过有效身份证件，请先变更证件有效期，如有疑问，请致电400-818-8168。";
        System.out.println(str.substring(2,str.length()));
        
    }
    
    public class demo {
        private String id;
        private Integer isd;
    }
}
