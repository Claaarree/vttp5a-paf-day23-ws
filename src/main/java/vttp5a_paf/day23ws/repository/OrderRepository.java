package vttp5a_paf.day23ws.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp5a_paf.day23ws.model.Order;

@Repository
public class OrderRepository {
    
    @Autowired
    private JdbcTemplate template;

    public Optional<Order> getOrderDetails(int order_id) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_ORDER_QUERY, order_id);

        rs.next();
        try {
            return Optional.of(Order.toOrder(rs));
        } catch (Exception e) {
            // why does the query gimme a null row when there's nothing?
            // because the aggregate sum() is used in the query..
            // if don't use then is fine and can do as per normal...
            // not really sure why tho...
            return Optional.empty();
        }
    }
}
