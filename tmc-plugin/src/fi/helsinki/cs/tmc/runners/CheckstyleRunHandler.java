package fi.helsinki.cs.tmc.runners;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import fi.helsinki.cs.tmc.model.ProjectMediator;
import fi.helsinki.cs.tmc.model.TmcProjectInfo;
import fi.helsinki.cs.tmc.stylerunner.CheckstyleRunner;
import fi.helsinki.cs.tmc.stylerunner.ValidationResult;
import fi.helsinki.cs.tmc.ui.ValidationResultDisplayer;

import org.netbeans.api.project.Project;

import org.openide.util.Exceptions;

public final class CheckstyleRunHandler {

    private final ValidationResultDisplayer validationResultDisplayer = ValidationResultDisplayer.getInstance();

    public void performAction(final Project project) {

        final TmcProjectInfo projectInfo = ProjectMediator.getInstance().wrapProject(project);

        try {
            final ValidationResult result = new CheckstyleRunner(projectInfo.getProjectDirAsFile()).run();
            validationResultDisplayer.showValidationResult(result);
        } catch (CheckstyleException exception) {
            Exceptions.printStackTrace(exception);
        }
    }
}
