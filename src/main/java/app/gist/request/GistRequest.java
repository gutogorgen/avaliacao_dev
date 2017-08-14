package app.gist.request;

import app.gist.entities.GistFile;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class GistRequest {

    private String user;
    private String passWord;
    private String description;
    private Boolean isPublic;
    private ArrayList<GistFile> files;

    public GistRequest(String user, String passWord, String description, Boolean isPublic, ArrayList<GistFile> files) {
        this.user = user;
        this.passWord = passWord;
        this.description = description;
        this.isPublic = isPublic;
        this.files = files;
    }

    public GistRequest() {
    }
}
