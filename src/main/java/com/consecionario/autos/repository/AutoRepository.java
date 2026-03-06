package com.consecionario.autos.repository;

import com.consecionario.autos.model.Auto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AutoRepository {

    private final JdbcTemplate jdbcTemplate;

    public AutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Auto> autoRowMapper = new RowMapper<Auto>() {
        @Override
        public Auto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Auto auto = new Auto();
            auto.setId(rs.getInt("id"));
            auto.setMarca(rs.getString("marca"));
            auto.setModelo(rs.getString("modelo"));
            auto.setPrecio(rs.getDouble("precio"));
            return auto;
        }
    };

    public List<Auto> findAll() {
        String sql = "SELECT * FROM autos";
        return jdbcTemplate.query(sql, autoRowMapper);
    }

    public int save(Auto auto) {
        String sql = "INSERT INTO autos (marca, modelo, precio) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, auto.getMarca(), auto.getModelo(), auto.getPrecio());
    }

    public int deleteById(Integer id) {
        String sql = "DELETE FROM autos WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}