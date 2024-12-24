package HollowKnight.model.credits;

import HollowKnight.model.game.elements.Knight.Knight;

public class Credits {
    private int score;
    private final int deaths;

    private String[] messages;
    private String[] names;

    private final int seconds;
    private final int minutes;

    public Credits(Knight player) {
        this.score =  player.getEnergy() - player.getNumberOfDeaths();
        this.deaths = player.getNumberOfDeaths();
        long duration = System.currentTimeMillis() - player.getBirthTime();
        this.seconds = (int) ((duration / 1000) % 60);
        this.minutes = (int) ((duration / 1000) / 60);

        String[] messages = new String[1];
        messages[0] = "Game Over!";
        this.messages = messages;

        String[] names = new String[4];
        names[0] = "Made By:";
        names[1] = "Andre Freitas";
        names[2] = "Joao Furtado";
        names[3] = "Joao Santos";
        this.names = names;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getNames() {
        return names;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }
}