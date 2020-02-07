package tema3;

/**
 *  @author Victor Tudose
 *  This class implements a player
 */
public class Player implements Comparable<Player>{
    String name;
    int score;

    /**
     * Constuctor that takes a line that describes the player
     * with fields separated by comma
     * @param line
     */
    public Player(String line) {
        String[] args=line.split(",\\s+");
        this.name = args[0];
        this.score = Integer.parseInt(args[1]);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Player o) {
        if(score > o.getScore())
            return 1;
        if(score < o.getScore())
            return -1;
        if(name.equals(o.getName()))
            return 0;
        return 1;
    }

    @Override
    public String toString() {
        return "{name: " + name +
                ", score: " + score +
                '}';
    }
}
