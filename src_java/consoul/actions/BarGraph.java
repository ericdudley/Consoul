package consoul.actions;

import java.util.HashMap;
import java.util.Map;

import static jcurses.system.InputChar.KEY_BACKSPACE;
import static jcurses.system.InputChar.KEY_DOWN;
import static jcurses.system.InputChar.KEY_UP;

/**
 * Created by eric on 5/2/17.
 */
public class BarGraph extends Action{
    public Map<String, Integer> getVals() {
        return vals;
    }

    private Map<String, Integer> vals;

    public BarGraph()
    {
        vals = new HashMap<>();
    }

    public void addVal(String name, int val)
    {
        vals.put(name, val);
    }


    @Override
    public void execute() {
        for (; ; ) {
            am.notifyChanged();
            int code = am.getInputCode();
            if (code == KEY_BACKSPACE)
                return;
        }
    }

    @Override
    public void reset() {

    }
}
