package consoul.actions;

import consoul.actions.Action;
import jcurses.system.InputChar;
import jdk.internal.util.xml.impl.Input;

import java.util.*;

import static jcurses.system.InputChar.KEY_BACKSPACE;
import static jcurses.system.InputChar.KEY_DOWN;
import static jcurses.system.InputChar.KEY_UP;

public class Form extends Action {
    public List<String> getFields() {
        return fields;
    }

    public List<String> getValues() {
        return values;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getCurrent() {
        return current;
    }

    protected List<String> fields;
    private List<String> values;
    private List<String> errors;
    private int current;

    public Form() {
        fields = new LinkedList<>();
        values = new LinkedList<>();
        errors = new LinkedList<>();
        fields.add("<Save>");
        values.add("");
        errors.add("");
        fields.add("<Back>");
        values.add("");
        errors.add("");
        current = 0;
    }

    public int numFields() {
        return fields.size();
    }

    public void addField(String name) {
        fields.add(numFields() - 2, name);
        values.add(numFields() - 2, "");
        errors.add(numFields() - 2, "");
    }

    private void addToField(int field, char ch) {
        values.set(field, values.get(field) + ch);
    }

    private void removeFromField(int field) {
        String str = values.get(field);
        values.set(field, str.substring(0, str.length() - 1));
    }

    /**
     * Move to previous field, wrap if necessary.
     */
    public void prev() {
        current = current == 0 ? this.fields.size() - 1 : current - 1;
    }

    /**
     * Move to next field, wrap if necessary.
     */
    public void next() {
        current = current == this.fields.size() - 1 ? 0 : current + 1;
    }

    @Override
    public void execute() {
        for (; ; ) {
            am.notifyChanged();
            InputChar code = am.getInput();
            values.set(numFields() - 2, "");
            if (code.getCode() == KEY_UP)
                prev();
            else if (code.getCode() == KEY_DOWN)
                next();
            else if (code.getCode() == KEY_BACKSPACE)
                removeFromField(current);
            else if (code.getCode() == 10) {
                if (current == numFields() - 1) {
                    return;
                } else if (current == numFields() - 2) {
                    save();
                    values.set(current, "Saved!");
                }

            } else if (!code.isSpecialCode() && current < numFields() - 2) {
                addToField(current, code.getCharacter());
            }
        }
    }

    @Override
    public void reset() {

    }
}