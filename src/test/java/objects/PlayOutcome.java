package objects;

public class PlayOutcome {

    private String your_weapon;
    private String computer_weapon;
    private String winner;

    public PlayOutcome() {
    }

    public PlayOutcome(String your_weapon, String computer_weapon, String winner) {
        super();
        this.your_weapon = your_weapon;
        this.computer_weapon = computer_weapon;
        this.winner = winner;
    }

    public String getYour_weapon() {
        return your_weapon;
    }

    public void setYour_weapon(String your_weapon) {
        this.your_weapon = your_weapon;
    }

    public String getComputer_weapon() {
        return computer_weapon;
    }

    public void setComputer_weapon(String computer_weapon) {
        this.computer_weapon = computer_weapon;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
