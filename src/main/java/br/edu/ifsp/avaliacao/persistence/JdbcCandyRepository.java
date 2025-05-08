package br.edu.ifsp.avaliacao.persistence;

import br.edu.ifsp.avaliacao.model.Candy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcCandyRepository implements CandyRepository {

    private final JdbcTemplate jdbc;

    public JdbcCandyRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Candy> findAll() {
        return jdbc.query("select id, name, description, price from candies", this::mapRow);
    }

    @Override
    public Candy findById(Long id) {
        return jdbc.queryForObject("select id, name, description, price from candies where id = ?", this::mapRow, id);
    }

    private Candy mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Candy(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price")
        );
    }
}
