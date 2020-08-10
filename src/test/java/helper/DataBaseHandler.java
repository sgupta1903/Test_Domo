package helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.EnvProperty;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.testng.Assert;


public class DataBaseHandler
{
    private EnvProperty envProperty;
    private String section;
    private JdbcTemplate jdbcTemplate;


    public DataBaseHandler(EnvProperty envProperty, String section) {
        this.envProperty = envProperty;
        this.section = section;
        createJdbcTemplate();
    }

    public DataBaseHandler verify_row_count(String sql, int rowCountExpected) {
        List<Map<String, Object>> results = null;
        try {
            results = jdbcTemplate.queryForList(sql);
        }
        catch (DataAccessException dae) {
            Assert.fail("Unable to execute query, sql is: " + sql);
        }

        Assert.assertTrue(results.size() == rowCountExpected, "Row count differs from expected, " +
                                                              "Expected: " + rowCountExpected + " Actual: " + results.size() + ", sql is: " + sql);
        return this;
    }

    public DataBaseHandler verify_row_count_greater_than_zero(String sql) {
        List<Map<String, Object>> results = null;
        try {
            results = jdbcTemplate.queryForList(sql);
        }
        catch (DataAccessException dae) {
            Assert.fail("Unable to execute query, sql is: " + sql);
        }

        Assert.assertTrue(results.size() > 0, "Row count is not greater than 0, sql is: " + sql);
        return this;
    }

    public DataBaseHandler insert_rows(String sql) {
        int result = 0;
        try {
            result = jdbcTemplate.update(sql);
        }
        catch (DataAccessException dae) {
            Assert.fail("Unable to execute query, sql is: " + sql);
        }
        Assert.assertTrue(result > 0, "Unable to insert row(s), sql is: " + sql);

        return this;
    }

    public DataBaseHandler update_rows(String sql) {
        try {
            jdbcTemplate.update(sql);
        }
        catch (DataAccessException dae) {
            Assert.fail("Unable to update row(s), sql is: " + sql);
        }

        return this;
    }

    public DataBaseHandler delete_rows(String sql) {
        try {
            jdbcTemplate.update(sql);
        }
        catch (DataAccessException dae) {
            Assert.fail("Unable to delete row(s), sql is: " + sql);
        }

        return this;
    }

    public DataBaseHandler execute_query(String sql) {
    try {
        jdbcTemplate.execute(sql);
    }
    catch (DataAccessException dae) {
        Assert.fail("Unable to execute query, error message is: " + dae.getMessage() + " sql is: " + sql);
    }

    return this;
}





    public DataBaseHandler verify_query_result(String sql, int rowCountExpected) {
        int value = 0;
        try {
           value = jdbcTemplate.queryForInt(sql);
           System.out.println("value="+value);
        }
        catch (DataAccessException dae) {
            Assert.fail("Unable to execute query, error message is: " + dae.getMessage() + " sql is: " + sql);
        }
        Assert.assertTrue(value == rowCountExpected, "Row count differs from expected, " +
                "Expected: " + rowCountExpected + " Actual: " + value + ", sql is: " + sql);
        return this;
    }

    public Map<String, Object> get_single_row(String sql) {
        try {
            return jdbcTemplate.queryForMap(sql);
        }
        catch (DataAccessException dae) {
            Assert.fail("Unable to execute query, sql is: " + sql);
        }

        return null; //should not happen
    }

    public List<Map<String, Object>> get_multiple_rows(String sql) {
        try {
            return jdbcTemplate.queryForList(sql);
        }
        catch (DataAccessException dae) {
            Assert.fail("Unable to execute query, sql is: " + sql);

        }

        return null; //should not happen
    }
    public int get_count(String sql) {
        int value = 0;
        try {
            value = jdbcTemplate.queryForInt(sql);
            System.out.println("value="+value);
        }
        catch (DataAccessException dae) {
            Assert.fail("Unable to execute query, error message is: " + dae.getMessage() + " sql is: " + sql);
        }
        return value;
    }


    private void createJdbcTemplate() {
    this.jdbcTemplate = new JdbcTemplate(getDataSource());

    }

    private BasicDataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(getConfigParamValue("driverClassName"));
        ds.setUrl(getConfigParamValue("url"));
        ds.setUsername(System.getenv(getConfigParamValue("dbUserName")));
        ds.setPassword(System.getenv(getConfigParamValue("dbPassword")));
        return ds;
    }

    private String getConfigParamValue(String param) {
        return getConfigParamValue(this.section,param);
    }

    private String getConfigParamValue(String section, String param) {
        return this.envProperty.getConfigPropertyValue(section, param);
    }

// for Data migration
    private Map<String, List<Object>> addToMap( String key, Object value, Map<String, List<Object>> map )
    {
        List<Object> list = new ArrayList<>();
        if( map.containsKey( key ) )
        {
            list = map.get( key );
            list.add( value );
        }
        else
        {
            list.add( value );
        }
        map.put( key, list );
        return map;
    }

    public Map executeSql( String sql )
    {

        Map map = (Map) jdbcTemplate.query( sql, new ResultSetExtractor()
        {
            @Override
            public Object extractData( ResultSet resultSet ) throws SQLException, DataAccessException
            {
                int colCount = resultSet.getMetaData().getColumnCount();
                Map<String, List<Object>> map = new HashMap<>();
                while( resultSet.next() )
                {
                    for( int i = 1; i <= colCount; i++ )
                    {
                        addToMap( resultSet.getMetaData().getColumnName( i ), resultSet.getObject( i ), map );
                    }
                }
                return map;
            }
        } );
        return map;
    }
}

