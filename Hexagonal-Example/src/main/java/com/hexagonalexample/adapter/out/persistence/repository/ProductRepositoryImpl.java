package com.hexagonalexample.adapter.out.persistence.repository;


import com.hexagonalexample.adapter.out.persistence.dto.ProductRepositoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final String CREATE_PRODUCT_QUERY = "INSERT INTO product_again" +
            " (name, quantity, price)" +
            " VALUES (:name, :quantity, :price)";
    private static final String FIND_PRODUCT_BY_ID_QUERY = "SELECT * FROM product_again WHERE id = :id";

    private static final String FIND_ALL_PRODUCTS = "SELECT * FROM product_again";

    private static final String FIND_PRODUCT_BY_NAME = "SELECT * FROM product WHERE name like :name";

    private static final String DELETE_PRODUCT = "DELETE FROM product_again WHERE id = :id";

    private static final String UPDATE_PRODUCT = "UPDATE product_again SET name = :name , quantity = :quantity, price = :price  WHERE id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<ProductRepositoryDTO> getById(int id) {
        try {
            SqlParameterSource params = new MapSqlParameterSource("id", id);
            ProductRepositoryDTO product = namedParameterJdbcTemplate.queryForObject(
                    FIND_PRODUCT_BY_ID_QUERY,
                    params,
                    (rs, rows) -> ProductRepositoryDTO.builder()
                            .id(Integer.parseInt(rs.getString("id")))
                            .name(rs.getString("name"))
                            .quantity(Integer.parseInt(rs.getString("quantity")))
                            .price(Double.parseDouble(rs.getString("price")))
                            .build());
            return Optional.of(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ProductRepositoryDTO> getAll() {
        SqlParameterSource params = new MapSqlParameterSource();
        List<ProductRepositoryDTO> result = namedParameterJdbcTemplate.query(
                FIND_ALL_PRODUCTS,
                params,
                (rs, rows) -> ProductRepositoryDTO.builder()
                        .id(Integer.parseInt(rs.getString("id")))
                        .name(rs.getString("name"))
                        .quantity(Integer.parseInt(rs.getString("quantity")))
                        .price(Double.parseDouble(rs.getString("price")))
                        .build());
        return result;
    }

    @Override
    public void save(ProductRepositoryDTO product) {
        final Map<String, Object> params = Map.of("name", product.getName(),
                "quantity", product.getQuantity(),
                "price", product.getPrice());
//        namedParameterJdbcTemplate.queryForObject(
//                CREATE_PRODUCT_QUERY,
//                params,
//                Integer.class);
        namedParameterJdbcTemplate.update(CREATE_PRODUCT_QUERY, params);

    }

    @Override
    public void update(ProductRepositoryDTO product) {
        if (getById(product.getId()).isPresent()) {
            SqlParameterSource params = new MapSqlParameterSource("name", product.getName())
                    .addValue("quantity", product.getQuantity())
                    .addValue("id", product.getId())
                    .addValue("price", product.getPrice());
            namedParameterJdbcTemplate.update(UPDATE_PRODUCT, params);
        } else {
            throw new EmptyResultDataAccessException("Not Found.", 0);
        }
    }

    @Override
    public void delete(int id) {
        if (getById(id).isPresent()) {
            SqlParameterSource params = new MapSqlParameterSource("id", id);
            namedParameterJdbcTemplate.update(DELETE_PRODUCT, params);
        } else {
            throw new EmptyResultDataAccessException("Not Found.", 0);
        }

    }
}
