package consoul.actions;

import consoul.actions.form.FormField;
import consoul.tools.ListWidget;
import jcurses.system.InputChar;

import java.util.*;

import static jcurses.system.InputChar.KEY_BACKSPACE;
import static jcurses.system.InputChar.KEY_DOWN;
import static jcurses.system.InputChar.KEY_UP;

public class Form extends Action {
    public List<FormField> getFields() {
        return list.getList();
    }

    public List<String> getSpecials() {
        return list.getSpecials();
    }
    public int getCurrent() {
        return list.getCurrent();
    }

    private ListWidget<FormField> list;

    public Form() {
        list = new ListWidget<FormField>() {
            @Override
            public List<String> toStrings() {
                List<String> strs = new ArrayList<>();
                for (FormField f : getList()) {
                    strs.add(f.getName() + ": " + f.getValue() + " | " + f.getError());
                }
                for (String s : getSpecials()) {
                    strs.add(s);
                }
                return strs;
            }
        };
        list.addSpecial("Save");
        list.addSpecial("Back");
    }

    public int numFields() {
        return list.size();
    }

    public void addField(String name) {
        list.addItem(new FormField(name));
    }

    public void addField(FormField f) {
        list.addItem(f);
    }
    private void addToField(int index, char ch) {
        if (list.isSpecial(index))
            return;
        FormField field = list.getList().get(index);
        field.setValue(field.getValue() + ch);
    }

    private void removeFromField(int index) {
        if (list.isSpecial(index))
            return;
        FormField field = list.getList().get(index);
        if (field.getValue().equals(""))
            return;
        field.setValue(field.getValue().substring(0, field.getValue().length() - 1));
    }

    @Override
    public void execute() {
        for (; ; ) {
            am.notifyChanged();
            InputChar code = am.getInput();
            if (code.getCode() == KEY_UP)
                list.prev();
            else if (code.getCode() == KEY_DOWN)
                list.next();
            else if (code.getCode() == KEY_BACKSPACE)
                removeFromField(getCurrent());
            else if (code.getCode() == 10) {
                if (list.isSpecial(getCurrent()) && list.getSpecial(getCurrent()).equals("Back")) {
                    return;
                } else if (list.isSpecial(getCurrent()) && list.getSpecial(getCurrent()).equals("Save")) {
                    if (validate())
                        save();
                }
            } else if (!code.isSpecialCode()) {
                addToField(getCurrent(), code.getCharacter());
            }
        }
    }

    @Override
    public void reset() {

    }

    public boolean validate() {
        boolean valid = true;
        for (FormField f : list.getList()) {
            if (!f.validate()) {
                f.setError("Invalid!");
                valid = false;
            } else
                f.setError("");
        }
        return valid;
    }
}