package app.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericOutput {

    private Boolean sucess;
    private String mensage;

    public GenericOutput(Boolean sucess, String mensage) {
        this.sucess = sucess;
        this.mensage = mensage;
    }

    public GenericOutput() {
    }
}
