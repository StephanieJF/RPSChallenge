package objects;

public class MyWeaponChoice {

    private Integer choice;

    public MyWeaponChoice() {
    }

    public MyWeaponChoice(Integer choice) {
        super();
        this.choice = choice;
    }

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }

    public MyWeaponChoice rock(){
        setChoice(1);
        return this;
    }

    public MyWeaponChoice paper(){
        setChoice(2);
        return this;
    }

    public MyWeaponChoice scissor(){
        setChoice(3);
        return this;
    }
}
