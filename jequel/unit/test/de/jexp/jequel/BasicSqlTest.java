package de.jexp.jequel;

import static de.jexp.jequel.expression.Expressions.*;
import static de.jexp.jequel.sql.Sql.*;
import static de.jexp.jequel.tables.TEST_TABLES.*;
import junit.framework.TestCase;

/**
 * @author mh14 @ jexp.de
 * @copyright (c) 2007 jexp.de
 * @since 17.10.2007 23:24:08
 */
public class BasicSqlTest extends TestCase {

    public void testBasicSql() {
        final SqlString sql = Select(ARTICLE.OID, ARTICLE.ARTICLE_NO).from(ARTICLE);

        assertEquals("select ARTICLE.OID, ARTICLE.ARTICLE_NO from ARTICLE", sql.toString());
    }

    public void testOrderBySql() {
        final SqlString sql = Select(ARTICLE.OID)
                .from(ARTICLE).order_by(ARTICLE.OID);

        assertEquals("select ARTICLE.OID from ARTICLE order by ARTICLE.OID", sql.toString());
    }

    public void testGroupBySql() {
        final SqlString sql = Select(ARTICLE.OID).from(ARTICLE)
                .group_by(ARTICLE.OID);

        assertEquals("select ARTICLE.OID from ARTICLE group by ARTICLE.OID", sql.toString());
    }

    public void testHavingSql() {
        final SqlString sql = Select(ARTICLE.OID).from(ARTICLE)
                .group_by(ARTICLE.OID).having(ARTICLE.OID.eq(ARTICLE.OID));

        assertEquals("select ARTICLE.OID from ARTICLE" +
                " group by ARTICLE.OID having ARTICLE.OID = ARTICLE.OID", sql.toString());
    }

    public void testWhereSql() {
        final SqlString sql =
                Select(ARTICLE.OID)
                        .from(ARTICLE, ARTICLE_COLOR)
                        .where(ARTICLE.OID.eq(ARTICLE_COLOR.ARTICLE_OID)
                                .and(ARTICLE.ARTICLE_NO.is_not(NULL)));

        assertEquals("select ARTICLE.OID" +
                " from ARTICLE, ARTICLE_COLOR" +
                " where ARTICLE.OID = ARTICLE_COLOR.ARTICLE_OID" +
                " and ARTICLE.ARTICLE_NO is not NULL", sql.toString());
    }
}
