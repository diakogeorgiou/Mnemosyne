package project.mobile.mnemosyne;

public class Card {

    private String ftext; // Front Text field (question) can be edited.
    private String fmedia; // Optional field import pictures or camera.
    private String btext; // Back Text (answer)
    private String bmedia; // (Optional Supplement) Back Media.
    private Integer nexttostudy; // This holds a value of "which card needs to be revised more often".
    private Boolean suspended; // Optional suspension of the card. Suspended cards will be ignored till there are no more new cards to be studied.

    public Card (String ftext, String fmedia, String btext, String bmedia, Integer nexttostudy) {
        this.ftext = ftext;
        this.fmedia = fmedia;
        this.btext = btext;
        this.bmedia = bmedia;
        this.nexttostudy = nexttostudy;
    }

    public void suspendCard() {
        suspended = true;
    }

    public void unsuspendCard() {
        suspended = false;
    }

    public String getFtext() {
        return ftext;
    }

    public void setFtext(String ftext) {
        this.ftext = ftext;
    }

    public String getFmedia() {
        return fmedia;
    }

    public void setFmedia(String fmedia) {
        this.fmedia = fmedia;
    }

    public String getBtext() {
        return btext;
    }

    public void setBtext(String btext) {
        this.btext = btext;
    }

    public String getBmedia() {
        return bmedia;
    }

    public void setBmedia(String bmedia) {
        this.bmedia = bmedia;
    }

    public Integer getNexttostudy() {
        return nexttostudy;
    }

    public void setNexttostudy(Integer nexttostudy) {
        this.nexttostudy = nexttostudy;
    }


}
