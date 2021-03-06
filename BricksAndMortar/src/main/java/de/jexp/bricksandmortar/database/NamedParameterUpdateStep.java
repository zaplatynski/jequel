package de.jexp.bricksandmortar.database;

import de.jexp.bricksandmortar.results.UpdateStepResult;

import java.util.Map;

/**
 * @author mh14 @ jexp.de
 * @since 15.11.2007 08:03:54 (c) 2007 jexp.de
 */
public class NamedParameterUpdateStep extends NamedParameterStep<UpdateStepResult> {
    protected UpdateStepResult execute(final Map<String, ?> row, final int count) {
        final int rowCount = getNamedJdbcTemplate().update(getQuery(), row);
        return new UpdateStepResult(createResultName(count),rowCount);
    }
}
