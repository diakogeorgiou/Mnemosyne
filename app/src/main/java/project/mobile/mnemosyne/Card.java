package project.mobile.mnemosyne;

public class Card {

    private String ftext; // Front Text field (question) can be edited.
    private String fmedia; // Optional field import pictures or camera.
    private String btext; // Back Text (answer)
    private String bmedia; // (Optional Supplement) Back Media.
    private Integer nexttostudy; // This holds a value of "which card needs to be revised more often".
    private Boolean suspend; // Optional suspension of the card. Suspended cards will be ignored till there are no more new cards to be studied.

    public Card (String ftext, String fmedia, String btext, String bmedia, Integer nexttostudy, Boolean suspend) {
        this.ftext = ftext;
        this.fmedia = fmedia;
        this.btext = btext;
        this.bmedia = bmedia;
        this.nexttostudy = nexttostudy;
        this.suspend = suspend;

    }

/*
.
.

  Get data from add card method of the main OR secondary activities
.
.
*/

}
