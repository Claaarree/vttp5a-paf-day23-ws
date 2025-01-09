package vttp5a_paf.day23ws.repository;

public class Queries {
    public static String SQL_ORDER_QUERY = """
        select o.id, o.order_date, o.customer_id,
            sum(od.unit_price * od.quantity) total_price,
            sum(od.quantity * p.standard_cost) cost_price
            from orders o 
            join order_details od
            on o.id = od.order_id
            join products p 
            on od.product_id = p.id
            where o.id = ?;
            """;
}
