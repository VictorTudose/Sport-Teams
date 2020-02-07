package tema3;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Victor Tudose
 * This class represents a Competition
 * It has a inner class Score that is a decortor class of Team , used for making the rankings
 * It has two ArrayLists of Score , one to retain teams as they enetered the competition
 * , and another one to retain ranking
 * Its constructor uses a Scanner and a PrintStream as input and output
 */
public class Competition {

    boolean gender;
    ArrayList<Team> teams;
    ArrayList<Score> scores;
    ArrayList<Score> ranking;
    PrintStream output;
    public static String type;

    class Score implements Comparable<Score>
    {
        Team team;
        int score;

        public Team getTeam() {
            return team;
        }
        public void incScore(int inc) {
            score+=inc;
        }
        public int getScore() {
            return score;
        }

        Score(Team team)
        {
            this.team=team;
        }

        void compete(Score other)
        {
            float score1=team.visit();
            float score2=other.getTeam().visit();
            if(score1 > score2 ) {
                incScore(3);
            } else {
                if(score1 == score2 )
                {
                    incScore(1);
                    other.incScore(1);
                }
                else {
                    other.incScore(3);
                }
            }
            Collections.sort(ranking);
            for (Score value : scores) {
                Team team = value.getTeam();
                team.observe(ranking.indexOf(value) + 1);
            }
        }

        @Override
        public int compareTo(Score o) {
            return Integer.compare(o.getScore(), score);
        }
    }

    /**
     * @param input
     * @param output
     */
    public Competition(Scanner input,PrintStream output) {
        this.output=output;

        teams=new ArrayList<>();
        scores=new ArrayList<>();
        ranking =new ArrayList<>();

        type=input.next().replaceAll(",","");

        String s_gender=input.next();
        if(s_gender.equals("masculin"))
            this.gender=true;

        while(input.hasNext()) {
            Team team= Team.getTeam(input.next());

            if(verifyTeam(team)) {
                teams.add(team);
                Score score=new Score(team);
                scores.add(score);
                ranking.add(score);
            }
        }
        input.close();
    }

    /**
     * This  function runs the competition
     */
    void compete() {
        int i;
        for(i=0;i<=scores.size();i++) {
            for (int j = i + 1; j < scores.size(); j++)
                scores.get(i).compete(scores.get(j));
        }

        i=0;
        for(Score score: ranking) {
            output.println(score.getTeam().teamName);
            i++;
            if(i==3)
                break;
        }

        for (i=0;i<scores.size();i++)
            output.println("Echipa "+ scores.get(i).getTeam().getTeamName()
                    +" a ocupat locul " + scores.get(i).getTeam().getRanking());
    }

    /**
     * Verifies if the team is adequate for the competition
     * @param team
     * @return true if the team is adequate for the competition
     */
    private boolean verifyTeam(Team team) {
        boolean ok=true;
        if(type.equals("basketball") && !(team instanceof  BasketballTeam) )
            ok=false;
        if(type.equals("football") && !(team instanceof  FootballTeam) )
            ok=false;
        if(type.equals("handball") && !(team instanceof  HandballTeam) )
            ok=false;
        if(team.getGender() != gender)
            ok=false;
        return ok;
    }
}
