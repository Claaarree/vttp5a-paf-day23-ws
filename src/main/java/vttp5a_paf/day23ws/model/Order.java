package vttp5a_paf.day23ws.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {
    private Integer order_id;
    private LocalDate order_date;
    private Integer customer_id;
    private Float total_price;
    private Float cost_price;

    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public LocalDate getOrder_date() {
        return order_date;
    }
    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }
    public Integer getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }
    public Float getTotal_price() {
        return total_price;
    }
    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }
    public Float getCost_price() {
        return cost_price;
    }
    public void setCost_price(Float cost_price) {
        this.cost_price = cost_price;
    }

    public static Order toOrder(SqlRowSet rs){
        Order o = new Order();
        LocalDate orderDate = ((LocalDateTime)rs.getObject("order_date")).toLocalDate();
        
        o.setOrder_id(rs.getInt("id"));
        o.setCustomer_id(rs.getInt("customer_id"));
        o.setOrder_date(orderDate);
        o.setTotal_price(rs.getFloat("total_price"));
        o.setCost_price(rs.getFloat("cost_price"));

        return o;
    }

    public static JsonObject toJson(Order o) {

        JsonObject jObject = Json.createObjectBuilder()
                .add("order_id", o.getOrder_id())
                .add("customer_id", o.getCustomer_id())
                .add("order_date", o.getOrder_date().format(DateTimeFormatter.ISO_DATE))
                .add("total_price", o.getTotal_price())
                .add("cost_price", o.getCost_price())
                .build();

        return jObject;
    }
}
