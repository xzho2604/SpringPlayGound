package com.example.demo;

import com.example.demo.model.Car;
import com.example.demo.service.GraphQLService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URISyntaxException;

@ExtendWith(SpringExtension.class)
public class DemoTest {
   @InjectMocks
   private GraphQLService qlService;

   @Test
   public void testResponse() throws URISyntaxException {
      var car = new Car("Nissan", 5000);
      var res = qlService.postRequest(car);
      System.out.println(res);
   }
}
