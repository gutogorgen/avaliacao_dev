package app.gist.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GistFile {

    private String name;
    private String content;

    public GistFile(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public GistFile() {
    }
}
