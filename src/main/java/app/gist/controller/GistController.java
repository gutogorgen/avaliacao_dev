package app.gist.controller;

import app.gist.model.GitHubConnection;
import app.util.GenericOutput;
import app.gist.entities.GistFile;
import app.gist.request.GistRequest;
import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.GistService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

@RestController
@RequestMapping(GistController.URI_GIST)
public class GistController {

    public static final String URI_GIST = "/gist";

    public GistController() {
    }

    /**
     * Method to create a new Gist
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public GenericOutput newGist(@RequestBody GistRequest request) {
        GenericOutput output = new GenericOutput();
        String user = request.getUser();
        String passWord = request.getPassWord();
        String description = request.getDescription();
        Boolean isPublic = request.getIsPublic();
        ArrayList<GistFile> files = request.getFiles();

        if (!GitHubConnection.basicGistValidation(description, files, output).getSucess()) {
            return output;
        }

        GitHubClient client;
        if (user != null && passWord != null) {
            client = GitHubConnection.getGitHubClient(user, passWord);
        } else {
            client = GitHubConnection.getGitHubClient("", "");
        }

        Gist gist = new Gist();

        if (isPublic != null) {
            gist.setPublic(isPublic);
        }

        gist.setDescription(description);

        if (!files.isEmpty()) {
            for (GistFile requestFile : files) {
                org.eclipse.egit.github.core.GistFile file = new org.eclipse.egit.github.core.GistFile();
                file.setFilename(requestFile.getName());
                file.setContent(requestFile.getContent());
                gist.setFiles(Collections.singletonMap(file.getFilename(), file));
            }
        }
        try {
            if (gist.getUser() == null) {
                output.setMensage(" Alert : User not found, creating anonymous Gist - ");
                gist = new GistService().createGist(gist);
            } else {
                gist = new GistService(client).createGist(gist);
            }
        } catch (UnknownHostException e) {
            output.setSucess(Boolean.FALSE);
            output.setMensage("Conection problems...");
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            output.setSucess(Boolean.FALSE);
            output.setMensage("Unexpected Exception...");
            return output;
        }
        output.setSucess(Boolean.TRUE);
        output.setMensage("Gist created at: " + gist.getHtmlUrl() + " ");
        return output;
    }

    /**
     * Method to list all comments of a Gist
     * @param gist_id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{gist_id}", method = RequestMethod.GET, produces = "application/json")
    public List<Comment> getComments(@PathVariable("gist_id") String gist_id) throws IOException {
        GistService gistService = new GistService();
        return gistService.getComments(gist_id);
    }
}
