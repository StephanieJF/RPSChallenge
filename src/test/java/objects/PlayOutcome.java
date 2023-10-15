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

    public String getComputer_weapon() {
        return computer_weapon;
    }

    public String getWinner() {
        return winner;
    }

}
