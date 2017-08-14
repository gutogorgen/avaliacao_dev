package app.icpc.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputLine {

    private Integer team;
    private Integer num_problem;
    private Integer time_resolver;
    private String letter;

    public InputLine(Integer team, Integer num_problem, Integer time_resolver, String letter) {
        this.team = team;
        this.num_problem = num_problem;
        this.time_resolver = time_resolver;
        this.letter = letter;
    }

    public InputLine() {
    }
}
