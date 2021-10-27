public class SolutionCard extends Card {

    public SolutionCard(String cardText){
        this.setCardText(cardText);
    }

    public String toString(){
        return getCardText();
    }
}
