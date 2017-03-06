package consoul.actions;

import java.util.List;

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

    }

    @Override
    public void reset() {

    }
}