package hr.algreba.pi.hardwareapp.repository;

import hr.algreba.pi.hardwareapp.domain.Hardware;
import hr.algreba.pi.hardwareapp.domain.Type;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository {

    private static final String TABLE_NAME = "hardware";
    private static final String GENERATED_KEY_COLUMN = "id";

    private static final String SELECT_ALL = "SELECT * from hardware";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcHardwareRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(GENERATED_KEY_COLUMN);
    }


    @Override
    public Set<Hardware> findAll() {
        return Set.copyOf(jdbcTemplate.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try {
            hardware.setId(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> update(String code, Hardware updatedHardware) {
        int executed = jdbcTemplate.update("UPDATE hardware set " +
                        "name = ?, " +
                        "type = ?, " +
                        "stock = ?, " +
                        "price = ? " +
                    "WHERE code = ?",
                updatedHardware.getName(),
                updatedHardware.getType().toString(),
                updatedHardware.getStock(),
                updatedHardware.getPrice(),
                code
        );

        return executed > 0 ? Optional.of(updatedHardware) : Optional.empty();
    }

    @Override
    public void deleteByCode(String code) {
        jdbcTemplate.update("DELETE FROM hardware WHERE code = ?", code);
    }

    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(
                rs.getLong("id"),
                rs.getString("name"),
                Type.valueOf(rs.getString("type")),
                rs.getString("code"),
                rs.getLong("stock"),
                rs.getBigDecimal("price")
        );
    }

    private long saveHardwareDetails(Hardware hardware) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", hardware.getName());
        values.put("type", hardware.getType());
        values.put("code", hardware.getCode());
        values.put("stock", hardware.getStock());
        values.put("price", hardware.getPrice());

        return simpleJdbcInsert.executeAndReturnKey(values).longValue();
    }

}
