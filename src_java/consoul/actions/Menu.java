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
    private ListWidget<Action> list;

    public Menu() {
        list = new ListWidget<>();
        list.addSpecial("Back");
    }

    /**
     * Adds action to option list.
     *
     * @param action Action to add.
     */
    public void addOption(Action action) {
        list.addItem(action);
    }

    /**
     * Removes action from option list.
     *
     * @param action Action to remove.
     */
    public void removeOption(Action action) {
        list.removeItem(action);
    }

    /**
     * Getter
     * @return List of actions in menu.
     */
    public List<String> getOptions()
    {
        return list.toStrings();
    }

    /**
     * Getter
     * @return Index of current action.
     */
    public int getCurrent()
    {
        return list.getCurrent();
    }

    /**
     * Route to the current menu option.
     * @param index Index of chosen action.
     */
    public void choose(int index)
    {
        if (!list.isSpecial(index))
            this.am.routeTo(list.getList().get(index));
        else if (list.getSpecial(index).equals("Back"))
            return;
    }

    @Override
    public void execute() {
        for (; ; ) {
            am.notifyChanged();
            int code = am.getInputCode();
            if (code == KEY_UP)
                list.prev();
            else if (code == KEY_DOWN)
                list.next();
            else if (code == 10) {
                choose(getCurrent());
                if (list.isSpecial(getCurrent()) && list.getSpecial(getCurrent()).equals("Back"))
                    return;
            }
        }
    }

    @Override
    public void reset() {

    }
}