/*
 * WorkflowContext.java
 *
 * Created on 5. Juli 2007, 13:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.jexp.bricksandmortar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailSendException;

import java.io.File;
import java.util.*;

public class WorkflowContext {
    protected final Log logger = LogFactory.getLog(getClass());

    private final Map<String, StepResult<?>> values = new HashMap<String, StepResult<?>>(10);
    private String directory;
    private boolean logResults;
    private final Collection<String> skipSteps=new HashSet<String>();
    public WorkflowContext() {
    }

    public void addResult(final String name, final StepResult<?> value) {
        if (logger.isInfoEnabled())
            logger.info("Adding " + name + " " + value.getClass().getSimpleName() + " to context ");
        values.put(name, value);
    }

    public <T extends StepResult<?>> T getResult(final String stepName) {
        return (T) values.get(stepName);
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public void addResult(final StepResult<?> stepResult) {
        addResult(stepResult.getName(), stepResult);
    }

    public String toString() {
        return getClass().getSimpleName() + ": " + values.toString();
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(final String directory) {
        this.directory = directory;
    }

    public void setLogResults(final boolean logResults) {
        this.logResults = logResults;
    }

    public boolean isLogResults() {
        return logResults;
    }

    public File getDirectoryFile() {
        final String directory = getDirectory();
        return directory!=null ? new File(directory) : new File(".");
    }
    public void addSkipSteps(final String...stepNames) {
        if (stepNames==null) return;
        skipSteps.addAll(Arrays.asList(stepNames));
    }
    public boolean skipStep(final String stepName) {
        return skipSteps.contains(stepName);
    }

    public Collection<String> getResultNames() {
        return values.keySet();
    }
}
