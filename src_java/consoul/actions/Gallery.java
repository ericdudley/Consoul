package consoul.actions;

import consoul.actions.Action;
import jcurses.system.InputChar;

import java.util.List;

import static jcurses.system.InputChar.*;


public class Gallery extends Action {
    private ListWidget<Image> list;
    private boolean show_info;

    public Gallery() {
        list = new ListWidget<>();
        show_info = false;
    }

    public List<Image> getImages() {
        return list.getList();
    }

    @Override
    public void preLoad() {
        //Populate list with images
    }

    @Override
    public void execute() {
        for (; ; ) {
            am.notifyChanged();
            InputChar code = am.getInput();
            if (code.getCode() == KEY_LEFT)
                list.prev();
            else if (code.getCode() == KEY_RIGHT)
                list.next();
            else if (code.getCode() == KEY_BACKSPACE)
                return;
            else if (!code.isSpecialCode() && code.getCharacter() == 'i') {
                show_info = !show_info;
            }
        }
    }

    @Override
    public void reset() {

    }
}