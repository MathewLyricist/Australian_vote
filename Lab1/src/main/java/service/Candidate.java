package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Candidate {
    private final String name;
    private List<String> bulleten = new ArrayList<>();
    private int votes;
    private double percentVotes;


    public Candidate(String name){
        this.name = name;
        this.votes = 0;
    }


    public int getVotes() {
        return votes;
    }


    public List<String> getBulleten() {
        return bulleten;
    }


    public String getName() {
        return name;
    }


    public void setBulleten(List<String> bulleten){
        this.bulleten = bulleten;
    }


    public void setVotes(int votes){
        this.votes=votes;
    }


    public void setPercentVotes(double percentVotes) {
        this.percentVotes = percentVotes;
    }


    public double getPercentVotes() {
        return percentVotes;
    }


    @Override
    public String toString() {
        return "Кандидат{" +
                "имя='" + name + '\'' +
                ", описание бюллетеня=" + Arrays.toString(new List[]{bulleten}) + '}';
    }
}
