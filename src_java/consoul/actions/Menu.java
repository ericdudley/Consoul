package consoul.actions;

import consoul.ApplicationManager;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

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

    /**
     * Initializes current to 0 and options list.
     */
    public Menu(ApplicationManager _am) {
        am = _am;
        current = 0;
        options = new ArrayList<>();
    }

    /**
     * Adds action to option list.
     *
     * @param action Action to add.
     */
    public void addOption(Action action) {
        options.add(action);
    }

    /**
     * Removes action from option list.
     *
     * @param action Action to remove.
     */
    public void removeOption(Action action) {
        options.remove(action);
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
        while (true) {
            am.notifyChanged();
            nextOption();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void reset() {

    }
}