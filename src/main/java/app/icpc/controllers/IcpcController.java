package app.icpc.controllers;

import app.icpc.requests.IcpcRequest;
import app.icpc.models.Solver;
import app.icpc.entities.InputLine;
import app.icpc.entities.Output;
import app.util.GenericOutput;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(IcpcController.URI_ICPC)
public class IcpcController {

    public static final String URI_ICPC = "/icpc";

    Solver solver;

    public IcpcController() {
        solver = new Solver();
    }

    /**
     * Show inputs ​​already processed
     * @return
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET, produces = "application/json")
    public Map<Integer, List<InputLine>> getHistoy() {
        return solver.getScoreBoardTeam();
    }

    /**
     * Show scoreboard
     * @return
     */
    @RequestMapping(value = "/scoreboard", method = RequestMethod.GET, produces = "application/json")
    public List<Output> getScoreBoards() {
        HashMap<Integer, Output> output = new HashMap<>();
        for (Map.Entry<Integer, List<InputLine>> scoreBoard : solver.getScoreBoardTeam().entrySet()) {
            solver.resolverOutput(scoreBoard.getValue(), output);
        }
        List<Output> outSort = new ArrayList<>();
        for(Map.Entry<Integer, Output> out : output.entrySet()){
            outSort.add(out.getValue());
        }
        Collections.sort(outSort, new Output());
        return outSort;
    }

    /**
     * Clear memory
     * @return
     */
    @RequestMapping(value = "/clear", method = RequestMethod.GET, produces = "application/json")
    public GenericOutput clear() {
        solver.getScoreBoardTeam().clear();
        solver.getProblemsSolved().clear();

        GenericOutput output = new GenericOutput();
        output.setSucess(Boolean.TRUE);
        output.setMensage("Data clean");

        return output;
    }

    /**
     * Process new queue
     * @param input
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public GenericOutput inputLine(@RequestBody IcpcRequest input) {
        GenericOutput output = new GenericOutput();

        for (InputLine inputLine : input.getInputLines()) {
            if (solver.getScoreBoardTeam().containsKey(inputLine.getTeam())) {
                if (solver.isResubmission(inputLine) != Boolean.TRUE) {
                    solver.getScoreBoardTeam().get(inputLine.getTeam()).add(inputLine);
                }
            } else {
                List<InputLine> newScoreBoard = new ArrayList<>();
                newScoreBoard.add(inputLine);
                solver.getScoreBoardTeam().put(inputLine.getTeam(), newScoreBoard);
            }
        }

        output.setSucess(Boolean.TRUE);
        output.setMensage("Values add in queue");

        return output;
    }

}
