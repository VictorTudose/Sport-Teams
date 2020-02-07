package tema3;

import java.util.Scanner;
/**
 * @author Victor Tudose
 * This class has the main purpose to solve the first part of t he task 1 , creating the teams
 * It implements the Factory pattern and the Singleton Pattern
 */
public class TeamFactory {
    public static TeamFactory teamFactory;

    public static TeamFactory getTeamFactory() {
        if(teamFactory==null)
            teamFactory=new TeamFactory();
        return teamFactory;
    }

    /**
     * Creates a new team
     * @param input
     */
    public void putTeam(Scanner input)
    {
        String option=input.next().replaceAll(",","");

        String line=input.nextLine();
        Team newTeam;
        switch (option) {
            case "football":
                newTeam = new FootballTeam(line);
                break;
            case "basketball":
                newTeam = new BasketballTeam(line);
                break;
            case "handball":
                newTeam = new HandballTeam(line);
                break;
            default:
                return;
        }
        for(int i=0;i< newTeam.getNumberOfPlayers();i++)
            newTeam.add(new Player(input.nextLine()));
    }
}
