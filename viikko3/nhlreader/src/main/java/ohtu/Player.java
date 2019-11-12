
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public String getNationality() {
        return this.nationality;
    }

    public String getTeam() {
        return this.team;
    }

    public int getGoals() {
        return this.goals;
    }

    public int getAssists() {
        return this.assists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return this.goals + this.assists;

    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Player o) {
        if (this.getPoints() == o.getPoints()) {
            if (this.getGoals() > o.getGoals()) {
                return -1;
            }
            return 0;
        } else if (this.getPoints() > o.getPoints()) {
            return -1;
        } else {
            return 1;
        }
    }
}
