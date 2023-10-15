import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import objects.MyWeaponChoice;
import objects.PlayOutcome;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class RockPaperScissorTests extends TestConfig {
    MyWeaponChoice myWeaponChoice = new MyWeaponChoice();
    @Test
    public void apiStatusCheck() {
        given()
                .body(myWeaponChoice.rock())
        .when()
                .post("")
        .then()
                .statusCode(200);
    }

    @Test
    public void verifyJSONSchema() {
        given()
                .body(myWeaponChoice.paper())
        .when()
                .post("")
        .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("RPSJsonSchema.json"));
    }

    @Test(dataProvider = "weapons")
    public void validResponseToValidWeapon(int num) {
        MyWeaponChoice weaponChoice = new MyWeaponChoice(num);
        Response response =
                given()
                        .body(weaponChoice)
                .when()
                        .post("");
        PlayOutcome playOutcome = response.getBody().as(PlayOutcome.class);

        switch (num) {
            case 1:
                if (playOutcome.getComputer_weapon().equals("ROCK")) {
                    Assert.assertEquals(playOutcome.getWinner(), "DRAW");
                } else if (playOutcome.getComputer_weapon().equals("PAPER")) {
                    Assert.assertEquals(playOutcome.getWinner(), "COMPUTER_WINS");
                } else {
                    Assert.assertEquals(playOutcome.getWinner(), "YOU_WIN");
                }
                break;
            case 2:
                if (playOutcome.getComputer_weapon().equals("PAPER")) {
                    Assert.assertEquals(playOutcome.getWinner(), "DRAW");
                } else if (playOutcome.getComputer_weapon().equals("SCISSOR")) {
                    Assert.assertEquals(playOutcome.getWinner(), "COMPUTER_WINS");
                } else {
                    Assert.assertEquals(playOutcome.getWinner(), "YOU_WIN");
                }
                break;
            case 3:
                if (playOutcome.getComputer_weapon().equals("SCISSOR")) {
                    Assert.assertEquals(playOutcome.getWinner(), "DRAW");
                } else if (playOutcome.getComputer_weapon().equals("ROCK")) {
                    Assert.assertEquals(playOutcome.getWinner(), "COMPUTER_WINS");
                } else {
                    Assert.assertEquals(playOutcome.getWinner(), "YOU_WIN");
                }
                break;
        }
    }

    @Test
    public void validResponsetoInvalidWeapon() {
        MyWeaponChoice invalidWeapon = new MyWeaponChoice(4);
        given()
                .body(invalidWeapon)
         .when()
                .post("")
         .then()
                .statusCode(422);
    }

    @Test
    public void randomnessTest(){
        int rockCount =0;
        int paperCount=0;
        int scissorCount=0;
        int totalPlays=0;
        for (int i =0; i<1000; i++){
            Response response =
                    given()
                            .body(myWeaponChoice.rock())
                    .when()
                            .post("");
            PlayOutcome playOutcome = response.getBody().as(PlayOutcome.class);
            switch (playOutcome.getComputer_weapon()){
                case "ROCK":
                    rockCount++;
                    break;
                case "PAPER":
                    paperCount++;
                    break;
                case "SCISSOR":
                    scissorCount++;
                    break;
            }
            totalPlays++;
        }
        float rockRatio= (float) rockCount /totalPlays;
        float paperRatio= (float) paperCount /totalPlays;
        float scissorRatio= (float) scissorCount /totalPlays;
        if((rockRatio > .30 && rockRatio < .40) && (paperRatio >.30 && paperRatio <.40)){
            System.out.println("Randomness Confirmed!");
        }
        System.out.println("rock:" + rockCount +  " paper:" + paperCount + " scissor:" + scissorCount);
        System.out.println(totalPlays);
        System.out.println("Rock ratio:" + rockRatio + " Paper ratio:" + paperRatio + " Scissor ratio:" + scissorRatio);
    }

    @DataProvider(name = "weapons")
    public Object[][] mydataprov() {
        return new Object[][]{
                {1}, {2}, {3}
        };
    }
}

