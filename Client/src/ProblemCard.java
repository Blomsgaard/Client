public class ProblemCard extends Card {

    public ProblemCard(String cardText){
        this.setCardText(cardText);
    }

    public String toString() {
        return getCardText();
    }
}