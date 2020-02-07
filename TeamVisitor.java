package tema3;

/**
 * @author Victor Tudose
 * TeamVisitor implements the visitor , strategy and singleton Patterns
 */
public class TeamVisitor {

    public static TeamVisitor teamVisitor;
    static Calculator calculator;

    /**
     *
     * Singleton get function
     * @return the instance
     */
    public static TeamVisitor getTeamVisitor()
    {
        if(teamVisitor==null)
            teamVisitor=new TeamVisitor();
        return teamVisitor;
    }

    private TeamVisitor() {
        chooseStrategy();
    }

    /**
     * Applies the chosen strategy on a team
     * @param team
     * @return
     */
    public float visit(Computable team)
    {
        if(team==null)
            return 0;
        return calculator.getScore(team);
    }

    /**
     * Chose the strategy according to the Competition type
     */
    private static void chooseStrategy()
    {
        if(calculator!=null)
            return;
        if(Competition.type.equals("basketball"))
        {
            calculator= team -> team.sum()/team.getNumberOfPlayers();
        }
        if(Competition.type.equals("football"))
        {
            calculator= team -> {
                if(team.isMaleTeam())
                    return team.sum()+team.maxScore();
                else
                    return team.sum()+team.minScore();
            };
        }
        if(Competition.type.equals("handball"))
        {
            calculator= team -> {
                if(team.isMaleTeam()){
                    return team.sum();
                }
                else {
                    return team.product();
                }
            };
        }

    }

}
