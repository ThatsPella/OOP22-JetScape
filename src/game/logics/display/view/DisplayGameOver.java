package game.logics.display.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.Function;

import game.utility.other.MenuOption;

/**
 * <p>This class contains what it is shown when Barry dies.</p>
 * 
 * <p>This class extends {@link Display}.</p>
 */
public class DisplayGameOver extends Display implements MenuDisplay {

    private static final int TEXT_TILE = 3;
    private static final int OPTION_TILE = 7;
    private static String title = "Game Over";
    private static String scoreString = "Your score was: ";
    private static String recordString = "NEW RECORD";
    private static String[] playingRecordString = {"BARRY COULD", "LIVE LONGER"};

    private static int playingRecord; // higher score obtained by playing consecutively
    private static boolean isNewPlayingRecord;

    private final int record; // absolute new record
    private boolean isNewRecord;

    private int finalScore;

    /**
     * {@link DisplayGameOver} constructor: add options to be shown.
     * 
     */
    public DisplayGameOver() {
        super();
        this.getOptions().add(MenuOption.RETRY);
        this.getOptions().add(MenuOption.MENU);

        //StatisticsReader.getRecord(); // TODO read record
        this.record = 0;
    }

    /**
     * {@inheritDoc}
     */
    public void drawScreen(final Graphics2D g, final MenuOption selected) {
        this.setSelectedOption(selected);

        // TITLE
        super.drawTitleText(g, title, Function.identity());

        // SCORE
        super.drawCenteredText(g, super.getTextFont(), DisplayGameOver.scoreString + this.finalScore, x -> x,
                DisplayGameOver.TEXT_TILE * getGameScreen().getTileSize(), super.getTextShift());

        // RECORD
        if (this.isNewRecord) {
            super.drawCenteredText(g, super.getTextFont(), DisplayGameOver.recordString, x -> x / 2,
                    (DisplayGameOver.TEXT_TILE + 1) * getGameScreen().getTileSize(), super.getTextShift());
        } else if (isNewPlayingRecord) {
            super.drawCenteredText(g, super.getTextFont(), DisplayGameOver.playingRecordString[0], x -> x / 2,
                    (DisplayGameOver.TEXT_TILE + 1) * getGameScreen().getTileSize(), super.getTextShift());
            super.drawCenteredText(g, super.getTextFont(), DisplayGameOver.playingRecordString[1], x -> x / 2,
                    (DisplayGameOver.TEXT_TILE + 2) * getGameScreen().getTileSize(), super.getTextShift());
        }

        // OPTIONS
        super.drawOptions(g, DisplayGameOver.OPTION_TILE);
    }

    // TODO: remove magic setting from here
    /*public void drawScreen(final Graphics2D g) {
        this.setSelectedOption(Optional.of(MenuOption.RETRY));
        this.drawScreen(g, Optional.of(this.getSelectedOption()));
    }*/
    /**
     * {@inheritDoc}
     */
    public void setFinalScore(final int finalScore) {
        this.finalScore = finalScore;

        if (finalScore > DisplayGameOver.playingRecord) {
            DisplayGameOver.isNewPlayingRecord = true;
            DisplayGameOver.playingRecord = finalScore;
        } else if (finalScore < DisplayGameOver.playingRecord) {
            DisplayGameOver.isNewPlayingRecord = false;
        }

        if (finalScore > this.record) {
            this.isNewRecord = true;
            //StatisticsReader.writeRecord(finalScore); // TODO write new record
        } else if (finalScore < this.record) {
            this.isNewRecord = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Color getShiftColor() {
        return Color.DARK_GRAY;
    }
}
