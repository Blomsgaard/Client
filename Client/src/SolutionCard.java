import java.io.Serializable;

public class SolutionCard extends Card implements Serializable {

    public SolutionCard(String cardText){
        this.setCardText(cardText);
    }

    public String toString(){
        return getCardText();
    }
}
