package app.icpc.models;

import app.icpc.entities.InputLine;
import app.icpc.entities.Output;
import app.icpc.entities.Problem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SolverTest {

    Solver solver;
    Map<Integer, List<Problem>> problemsSolved;
    InputLine inputLine;
    List<InputLine> inputs;
    HashMap<Integer, Output> outputList;

    @Before
    public void setUp() throws Exception {
        solver = new Solver();
        inputLine = new InputLine();
        problemsSolved = new HashMap<>();
        inputs = new LinkedList<>();
        outputList = new HashMap<>();

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * FIRST TO RESOLVER TEST
     * @throws Exception
     */
    @Test
    public void isFirtToResolver() throws Exception {
        InputLine line1 = new InputLine();
        line1.setTeam(1);
        line1.setNum_problem(1);

        Assert.assertTrue(solver.isFirstToResolver(1));
    }

    /**
     * PENALITY TEST
     * @throws Exception
     */
    @Test
    public void resolverOutput() throws Exception {
        List<InputLine> inputs = new LinkedList<>();
        InputLine inputLine = new InputLine();

        inputLine.setTeam(1);
        inputLine.setNum_problem(1);
        inputLine.setTime_resolver(10);
        inputLine.setLetter("I");
        inputs.add(inputLine);

        solver.resolverOutput(inputs, outputList);
        Assert.assertTrue(outputList.get(inputLine.getTeam()).getSum_penaltys() == 20);
        Assert.assertTrue(outputList.get(inputLine.getTeam()).getNum_problems() == 0);
    }

    /**
     *  OUTPUT TEST
     * @throws Exception
     */
    @Test
    public void resolverOutput2() throws Exception {
        inputLine.setTeam(1);
        inputLine.setNum_problem(1);
        inputLine.setTime_resolver(10);
        inputLine.setLetter("C");
        inputs.add(inputLine);

        solver.resolverOutput(inputs, outputList);
        Assert.assertTrue(outputList.get(inputLine.getTeam()).getSum_penaltys() == 10);
        Assert.assertTrue(outputList.get(inputLine.getTeam()).getNum_problems() == 1);
    }

    /**
     * TEST WITH THE SAME VALUES OF INPUT AND OUTPUT OF THE EXAMPLE ACTIVITY
     * @throws Exception
     */
    @Test
    public void resolverOutput3() throws Exception {
        InputLine line1 = new InputLine();
        line1.setTeam(1);
        line1.setNum_problem(2);
        line1.setTime_resolver(10);
        line1.setLetter("I");

        InputLine line2 = new InputLine();
        line2.setTeam(3);
        line2.setNum_problem(1);
        line2.setTime_resolver(11);
        line2.setLetter("C");

        InputLine line3 = new InputLine();
        line3.setTeam(1);
        line3.setNum_problem(2);
        line3.setTime_resolver(19);
        line3.setLetter("R");

        InputLine line4 = new InputLine();
        line4.setTeam(1);
        line4.setNum_problem(2);
        line4.setTime_resolver(21);
        line4.setLetter("C");

        InputLine line5 = new InputLine();
        line5.setTeam(1);
        line5.setNum_problem(1);
        line5.setTime_resolver(25);
        line5.setLetter("C");

        inputs.add(line1);
        inputs.add(line2);
        inputs.add(line3);
        inputs.add(line4);
        inputs.add(line5);

        solver.resolverOutput(inputs, outputList);
        Assert.assertTrue(outputList.get(1).getTeam() == 1);
        Assert.assertTrue(outputList.get(1).getSum_penaltys() == 66);
        Assert.assertTrue(outputList.get(1).getNum_problems() == 2);
        Assert.assertTrue(outputList.get(3).getTeam() == 3);
        Assert.assertTrue(outputList.get(3).getSum_penaltys() == 11);
        Assert.assertTrue(outputList.get(3).getNum_problems() == 1);
    }

    /**
     * OUTPUT TEST
     * @throws Exception
     */
    @Test
    public void resolverOutput4() throws Exception {
        InputLine line1 = new InputLine();
        line1.setTeam(1);
        line1.setNum_problem(1);
        line1.setTime_resolver(10);
        line1.setLetter("C");

        InputLine line2 = new InputLine();
        line2.setTeam(2);
        line2.setNum_problem(1);
        line2.setTime_resolver(5);
        line2.setLetter("C");

        InputLine line3 = new InputLine();
        line3.setTeam(3);
        line3.setNum_problem(1);
        line3.setTime_resolver(3);
        line3.setLetter("C");

        InputLine line4 = new InputLine();
        line4.setTeam(2);
        line4.setNum_problem(2);
        line4.setTime_resolver(15);
        line4.setLetter("C");

        InputLine line5 = new InputLine();
        line5.setTeam(3);
        line5.setNum_problem(2);
        line5.setTime_resolver(15);
        line5.setLetter("C");

        inputs.add(line1);
        inputs.add(line2);
        inputs.add(line3);
        inputs.add(line4);
        inputs.add(line5);

        solver.resolverOutput(inputs, outputList);
        Assert.assertTrue(outputList.get(1).getSum_penaltys() == 10);
        Assert.assertTrue(outputList.get(1).getNum_problems() == 1);

        Assert.assertTrue(outputList.get(2).getSum_penaltys() == 20);
        Assert.assertTrue(outputList.get(2).getNum_problems() == 2);

        Assert.assertTrue(outputList.get(3).getSum_penaltys() == 18);
        Assert.assertTrue(outputList.get(3).getNum_problems() == 2);
    }

    /**
     * TEST RESUBMISSION NULL
     * @throws Exception
     */
    @Test
    public void isResubmissionNULL() throws Exception {
        Assert.assertTrue(solver.isResubmission(null) == Boolean.FALSE);
    }

    /**
     * RESUBMITION TEST PROBLEM TRUE
     * @throws Exception
     */
    @Test
    public void isResubmissionProblemNumberTrue() throws Exception {
        solver.setProblemsSolved(problemsSolved);

        inputLine.setTeam(1);
        inputLine.setNum_problem(1);

        List<Problem> problems = new LinkedList<>();

        Problem problem = new Problem();
        problem.setSolved(Boolean.TRUE);
        problem.setTeam(1);
        problem.setId(1);
        problems.add(problem);
        problemsSolved.put(problem.getId(), problems);

        Assert.assertTrue(solver.isResubmission(inputLine) == Boolean.TRUE);
    }

    /**
     * RESUBMITION TEST NUMBER FALSE
     * @throws Exception
     */
    @Test
    public void isResubmissionProblemNumberFalse() throws Exception {
        solver.setProblemsSolved(problemsSolved);

        InputLine inputLine = new InputLine();
        inputLine.setTeam(1);
        inputLine.setNum_problem(1);

        List<Problem> problems = new LinkedList<>();

        Problem problem = new Problem();
        problem.setSolved(Boolean.TRUE);
        problem.setTeam(1);
        problem.setId(2);
        problems.add(problem);
        problemsSolved.put(problem.getId(), problems);

        Assert.assertTrue(solver.isResubmission(inputLine) == Boolean.FALSE);
    }

    /**
     * RESUBMITION TEST TIME FALSE
     * @throws Exception
     */
    @Test
    public void isResubmissionTimeFalse() throws Exception {
        solver.setProblemsSolved(problemsSolved);

        inputLine.setTeam(1);
        inputLine.setNum_problem(1);

        List<Problem> problems = new LinkedList<>();

        Problem problem = new Problem();
        problem.setSolved(Boolean.TRUE);
        problem.setTeam(2);
        problem.setId(1);
        problems.add(problem);
        problemsSolved.put(problem.getId(), problems);

        Assert.assertTrue(solver.isResubmission(inputLine) == Boolean.FALSE);
    }


}