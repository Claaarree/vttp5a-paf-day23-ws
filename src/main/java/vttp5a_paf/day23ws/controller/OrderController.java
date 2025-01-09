package vttp5a_paf.day23ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.JsonObject;
import vttp5a_paf.day23ws.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/total")
    public String redirect(@RequestParam int order_id) {
       
        return "redirect:/order/total/" + order_id;
    }

    @ResponseBody
    @GetMapping(path = "/total/{order_id}", produces = "application/json")
    public ResponseEntity<String> getOrderDetails(@PathVariable int order_id) {
        JsonObject jObject = orderService.getOrderDetails(order_id);

        try {
            jObject.getString("error");
            return ResponseEntity.badRequest().body(jObject.toString());
        } catch (Exception e) {
            return ResponseEntity.ok().body(jObject.toString());
        }
    }
}
