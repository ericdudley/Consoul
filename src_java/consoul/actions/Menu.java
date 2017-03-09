package consoul.actions;

import consoul.ApplicationManager;
import jcurses.system.InputChar;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static jcurses.system.InputChar.*;

/**
 * Base menu class that links to consoul.actions.
 *
 * @author Eric Dudley
 * @version 3/6/17
 */
public class Menu extends Action
{
    private List<Action> options;
    private int current;

    public Menu() {
        super();
        current = 0;
        options = new ArrayList<>();
        Filler back = new Filler();
        back.setApplicationManager(this.am);
        back.setName("Back");
        options.add(back);
    }

    /**
     * Adds action to option list.
     *
     * @param action Action to add.
     */
    public void addOption(Action action) {
        if (options.size() >= 1)
            options.add(options.size() - 1, action);
    }

    /**
     * Removes action from option list.
     *
     * @param action Action to remove.
     */
    public void removeOption(Action action) {
        if (options.size() >= 2)
            options.remove(options.size() - 2);
    }

    /**
     * Getter
     * @return List of actions in menu.
     */
    public List<Action> getOptions()
    {
        return this.options;
    }

    /**
     * Getter
     * @return Index of current action.
     */
    public int getCurrent()
    {
        return this.current;
    }

    /**
     * Move to previous menu option, wrap if necessary.
     */
    public void prevOption()
    {
        current = current == 0 ? this.options.size()-1 : current-1;
    }

    /**
     * Move to next menu option, wrap if necessary.
     */
    public void nextOption()
    {
        current = current == this.options.size()-1 ? 0 : current+1;
    }

    /**
     * Route to the current menu option.
     * @param index Index of chosen action.
     */
    public void choose(int index)
    {
        this.am.routeTo(this.options.get(index));
    }

    @Override
    public void execute() {
        for (; ; ) {
            am.notifyChanged();
            int code = am.getInputCode();
            if (code == KEY_UP)
                prevOption();
            else if (code == KEY_DOWN)
                nextOption();
            else if (code == 10) {
                if (current == options.size() - 1) {
                    return;
                } else {
                    choose(current);
                }
            }
        }

    }

    @Override
    public void reset() {

    }
}