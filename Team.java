package tema3;
import java.io.PrintStream;
import java.util.*;

/**
 * @author Victor Tudose
 * This class implements the Team class
 * It has an inner static class named BiDiMap that is a duble HashMap between the names and the teams
 * Every time a team is created it is added to the BiDiMap
 */
public abstract class Team implements CompetitionObeserver , Computable {

    String teamName;
    boolean gender;
    int numberOfPlayers;
    ArrayList<Player> players;
    int ranking;

    static class BiDiMap
    {
        public static HashMap<String,Team> teamHashMap;
        public static HashMap<Team,String> revMap;
        public static int count;

        public static void init() {
            teamHashMap=new HashMap<>();
            revMap=new HashMap<>();
            count=0;
        }

        public static Team get(String teamName)
        {
            return teamHashMap.get(teamName);
        }

        public static HashMap<Team, String> getRevMap() {
            return revMap;
        }

        public static void put(Team team)
        {
            revMap.put(team,team.getTeamName());
            teamHashMap.put(team.getTeamName(),team);
            count++;
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public int getRanking() {
        return ranking;
    }

    public static void initTeams()
    {
        BiDiMap.init();
    }

    public static Team getTeam(String teamName) {
        return BiDiMap.get(teamName);
    }

    /**
     * This function prints at output the Teams
     * @param output
     */
    public static void showAll(PrintStream output) {
        for(Team team : BiDiMap.getRevMap().keySet()) output.print(team);
    }

    /**
     * Loads the teams
     * @param input
     */
    public static void loadAll(Scanner input) {
        if(input == null)
            return;
        while(input.hasNext())
            TeamFactory.getTeamFactory().putTeam(input);
    }

    /**
     * Team is loaded from the line and its player are to be then added
     * This constuctor is only used in TeamFactory
     * @param line
     */
    public Team(String line) {
        String[] args=line.split(",\\s+");
       this.teamName=args[0].replaceFirst("\\s","");
       this.numberOfPlayers=Integer.parseInt(args[2]);
        if(args[1].equals("masculin"))
            this.gender=true;
        players=new ArrayList<>();
        BiDiMap.put(this);
    }

    public void add(Player player) {
        this.players.add(player);
    }

    public boolean getGender() {
        return gender;
    }
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("{teamName: ");
        sb.append(teamName);
        sb.append(", gender:");
        if(gender)
            sb.append(" masculin");
        else
            sb.append(" feminin");
        sb.append(", numberOfPlayers: ");
        sb.append(numberOfPlayers);
        sb.append(", players: [");
        for(Player player:players) {
            sb.append(player.toString());
            if(player!=players.get(players.size()-1))
                sb.append(", ");
        }
        sb.append("]}\n");
        return sb.toString();

    }

    @Override
    public float maxScore() {
        float max=0.0f;
        for(Player player: players) {
            if (player.getScore() > max)
                max = player.getScore();
        }
        return max;
    }

    @Override
    public float minScore() {
        float min=10000.0f;
        for(Player player: players) {
            if (player.getScore() < min)
                min = player.getScore();
        }
        return min;
    }

    @Override
    public void observe(int i) {
        ranking=i;
    }

    /**
     * This is the visit function
     * @return the score of the function
     */
    @Override
    public float visit() {
        return TeamVisitor.getTeamVisitor().visit(this);
    }

    /**
     * Sum of the players scores
     * @return
     */
    @Override
    public float sum() {
        float sum=0.0f;
        for(Player p: players)
            sum+=p.getScore();
        return  sum;
    }

    /**
     * Product of players scores
     * @return
     */
    @Override
    public float product() {
        float prod=1.0f;
        for(Player p: players)
            prod*=p.getScore();
        return prod;
    }

    /**
     * Is the team a MaleTeam
     * @return
     */
    @Override
    public boolean isMaleTeam() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        boolean equals=false;
        if (gender == team.gender)
            if (numberOfPlayers == team.numberOfPlayers) equals = ((Team) o).teamName.equals(teamName);
        return equals;
    }

    @Override
    public int hashCode() {
        return Team.BiDiMap.count;
    }
}
