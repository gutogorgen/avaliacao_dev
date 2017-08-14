package app.icpc.models;


import app.icpc.entities.InputLine;
import app.icpc.entities.Output;
import app.icpc.entities.Problem;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Solver {

    private Map<Integer, List<InputLine>> scoreBoardTeam;
    private Map<Integer, List<Problem>> problemsSolved;


    public Solver() {
        scoreBoardTeam = new HashMap<>();
        problemsSolved = new HashMap<>();
    }

    /**
     * Check if problem has already been resolved
     * @param idProblem
     * @return
     */
    public Boolean isFirstToResolver(Integer idProblem) {
        if (problemsSolved.get(idProblem) != null && !problemsSolved.get(idProblem).isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Build ScoreBoard
     * @param value
     * @param outputList
     * @return
     */
    public HashMap<Integer, Output> resolverOutput(List<InputLine> value, HashMap<Integer, Output> outputList) {
        if(value == null){
            return outputList;
        }
        for (InputLine inputLine : value) {
            Output output;
            if (outputList.containsKey(inputLine.getTeam())) {
                output = outputList.get(inputLine.getTeam());
            } else {
                output = new Output(0, 0, 0);
                output.setTeam(inputLine.getTeam());
                outputList.put(inputLine.getTeam(), output);
            }
            String letter = inputLine.getLetter();
            if (letter.equalsIgnoreCase("I")) {
                output.setSum_penaltys(output.getSum_penaltys() + 20);
            } else if (letter.equalsIgnoreCase("C")) {
                output.setNum_problems(output.getNum_problems() + 1);
                output.setSum_penaltys(output.getSum_penaltys() + inputLine.getTime_resolver());

                Problem problem = new Problem(inputLine.getNum_problem(),
                        Boolean.TRUE, inputLine.getTeam(), isFirstToResolver(inputLine.getNum_problem()));

                List<Problem> problemList;
                if (problemsSolved.get(problem.getId()) != null && !problemsSolved.get(problem.getId()).isEmpty()) {
                    problemList = problemsSolved.get(problem.getId());
                } else {
                    problemList = new LinkedList<>();
                    problemsSolved.put(problem.getId(), problemList);
                }
                problemList.add(problem);
            }
        }
        return outputList;
    }

    /**
     * Check if input has already ben send
     * @param inputLine
     * @return
     */
    public Boolean isResubmission(InputLine inputLine) {
        if(inputLine != null){
            if (problemsSolved.get(inputLine.getNum_problem()) != null && !problemsSolved.get(inputLine.getNum_problem()).isEmpty()) {
                List<Problem> resolutions = problemsSolved.get(inputLine.getNum_problem());
                for (Problem problemSolved : resolutions) {
                    if (problemSolved.getTeam() == inputLine.getTeam()) {
                        System.out.println("Submission ignored");
                        return Boolean.TRUE;
                    }
                }
            }
        }
        return Boolean.FALSE;
    }

}
