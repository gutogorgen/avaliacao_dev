package app.gist.model;

import app.gist.entities.GistFile;
import app.util.GenericOutput;
import org.eclipse.egit.github.core.client.GitHubClient;

import java.util.ArrayList;

public class GitHubConnection {

    /**
     * Method to establish a connection with specific user
     * @param name
     * @param password
     * @return
     */
    public static final GitHubClient getGitHubClient(String name, String password) {
        return new GitHubClient().setCredentials(name, password);
    }

    /**
     * Method to validate basic input for new Gist
     * @param description
     * @param files
     * @param output
     * @return
     */
    public static final GenericOutput basicGistValidation(String description, ArrayList<GistFile> files, GenericOutput output) {
        if (description == null) {
            output.setSucess(Boolean.FALSE);
            output.setMensage("Error : Incomplete input structure - Missing a description");
            return output;
        }
        if (files == null) {
            output.setSucess(Boolean.FALSE);
            output.setMensage("Error : Incomplete input structure - Missing a file");
            return output;
        }
        output.setSucess(Boolean.TRUE);
        return output;
    }
}
