package app.icpc.requests;

import app.icpc.entities.InputLine;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class IcpcRequest {

    private ArrayList<InputLine> inputLines;

    public IcpcRequest(ArrayList<InputLine> inputLines) {
        this.inputLines = inputLines;
    }

    public IcpcRequest() {
    }
}
