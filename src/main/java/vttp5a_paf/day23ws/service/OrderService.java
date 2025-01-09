package vttp5a_paf.day23ws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp5a_paf.day23ws.model.Order;
import vttp5a_paf.day23ws.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public JsonObject getOrderDetails(int order_id) {
        Optional<Order> opt = orderRepository.getOrderDetails(order_id);

        return opt.map(o -> Order.toJson(o))
            .orElse(Json.createObjectBuilder()
            .add("error", "Order id entered is not valid!")
            .build());
    }
}
