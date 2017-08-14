package app.icpc.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
public class Output implements Comparator<Output> {

    private Integer team;
    private Integer num_problems;
    private Integer sum_penaltys;

    public Output(Integer team, Integer num_problems, Integer sum_penaltys) {
        this.team = team;
        this.num_problems = num_problems;
        this.sum_penaltys = sum_penaltys;
    }

    public Output() {
    }

    /**
     * Order Score Output,
     * 1º - numbers of problems resolved,
     * 2º - total penalty time,
     * 3º - number equip
     * @param out1
     * @param out2
     * @return
     */
    @Override
    public int compare(Output out1, Output out2) {
        if(out1.getNum_problems() > out2.num_problems){
            return -1;
        } else if (out1.getNum_problems() == out2.num_problems ){
            if(out1.getSum_penaltys() < out2.getSum_penaltys()){
                return -1;
            } else if (out1.getSum_penaltys() == out2.getSum_penaltys()) {
                if(out1.getTeam() < out2.getTeam()){
                    return -1;
                } else {
                    return +1;
                }
            } else {
                return +1;
            }
        } else {
            return +1;
        }
    }
}
