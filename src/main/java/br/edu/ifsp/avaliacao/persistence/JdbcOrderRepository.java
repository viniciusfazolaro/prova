package br.edu.ifsp.avaliacao.persistence;

import br.edu.ifsp.avaliacao.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final SimpleJdbcInsert orderInserter;

    public JdbcOrderRepository(JdbcTemplate jdbc) {
        orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("orders").usingGeneratedKeyColumns("id");
    }

    @Override
    public Order save(Order order) {
        Map<String, Object> values = new HashMap<>();

        values.put("id", order.getId());
        values.put("customerName", order.getCustomerName());
        values.put("address", order.getAddress());
        values.put("candyId", order.getCandy().getId());
        values.put("paymentMethod", order.getPaymentMethod());
        values.put("phoneNumber", order.getPhoneNumber());

        long id = orderInserter.executeAndReturnKey(values).longValue();
        order.setId(id);
        return order;
    }
}
