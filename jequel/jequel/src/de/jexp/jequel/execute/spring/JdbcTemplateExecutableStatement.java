package de.jexp.jequel.execute.spring;

import de.jexp.jequel.execute.core.AbstractExecutableStatement;
import de.jexp.jequel.jdbc.beanprocessor.BeanRowHandler;
import de.jexp.jequel.jdbc.beanprocessor.BeanRowMapper;
import de.jexp.jequel.jdbc.beanprocessor.ResultSetBeanRowHandler;
import de.jexp.jequel.jdbc.beanprocessor.ResultSetBeanRowMapper;
import de.jexp.jequel.jdbc.valuehandler.ResultSetValueRowHandler;
import de.jexp.jequel.jdbc.valuehandler.ResultSetValueRowMapper;
import de.jexp.jequel.jdbc.valuehandler.ValueRowHandler;
import de.jexp.jequel.jdbc.valuehandler.ValueRowMapper;
import de.jexp.jequel.sql.Sql;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * @author mh14 @ jexp.de
 * @since 02.11.2007 19:45:17 (c) 2007 jexp.de
 */
@SuppressWarnings({"unchecked"})
public class JdbcTemplateExecutableStatement extends AbstractExecutableStatement {
    private final JdbcTemplate template;

    public JdbcTemplateExecutableStatement(final DataSource dataSource, final Sql sql) {
        super(dataSource, sql);
        template = new JdbcTemplate(getDataSource());
    }

    public int getValue() {
        return template.queryForInt(getSqlString());
    }

    public <T> T getValue(final Class<T> type) {
        return (T) template.queryForObject(getSqlString(), type);
    }

    public <T> Collection<T> mapRows(final RowMapper rowMapper) {
        return template.query(getSqlString(), rowMapper);
    }

    public void processRows(final RowCallbackHandler rowHandler) {
        template.query(getSqlString(), rowHandler);
    }

    public <I, O> Collection<O> mapBeans(final BeanRowMapper<I, O> beanRowMapper) {
        return template.query(getSqlString(), new ResultSetBeanRowMapper<I, O>(beanRowMapper));
    }

    public <O> Collection<O> mapValues(final ValueRowMapper<O> valueRowMapper) {
        return template.query(getSqlString(), new ResultSetValueRowMapper<O>(valueRowMapper));
    }

    public <I> void handleBeans(final BeanRowHandler<I> beanRowHandler) {
        template.query(getSqlString(), new ResultSetBeanRowHandler<I>(beanRowHandler));
    }

    public void handleValues(final ValueRowHandler valueRowHandler) {
        template.query(getSqlString(), new ResultSetValueRowHandler(valueRowHandler));
    }

    public JdbcTemplate getTemplate() {
        return template;
    }
}
