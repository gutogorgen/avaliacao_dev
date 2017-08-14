package app.icpc.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Problem {

    private Integer id;
    private Boolean solved;
    private Integer team;
    private Boolean first_to_solve;

    public Problem(Integer id, Boolean solved, Integer team, Boolean first_to_solve ) {
        this.id = id;
        this.solved = solved;
        this.team = team;
        this.first_to_solve = first_to_solve;
    }

    public Problem() {
    }




}
