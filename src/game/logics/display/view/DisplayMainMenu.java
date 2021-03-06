package game.logics.display.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.UnaryOperator;

import game.utility.other.MenuOption;

/**
 * <p>This class is used to display the main menu.</p>
 * 
 * <p>This class extends {@link Display}.</p>
 */
public class DisplayMainMenu extends Display implements MenuDisplay {

    private static final String GAME_NAME = "JetScape";

    /**
     * {@link DisplayMainMenu} constructor: add options to be shown.
     */
    public DisplayMainMenu() {
        super();

        this.getOptions().add(MenuOption.START);
        this.getOptions().add(MenuOption.SETTINGS);
        this.getOptions().add(MenuOption.RECORDS);
        this.getOptions().add(MenuOption.QUIT);
    }

    /**
     * {@inheritDoc}
     */
    public void drawScreen(final Graphics2D g, final MenuOption selected) {
        this.setSelectedOption(selected);

        // TITLE
        super.drawTitleText(g, GAME_NAME, UnaryOperator.identity());

        // OPTIONS
        super.drawOptions(g);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Color getShiftColor() {
        return Color.DARK_GRAY;
    }
}
