package consoul;

import consoul.actions.Action;
import consoul.actions.Form;
import consoul.actions.Menu;
import jcurses.system.CharColor;

/**
 * Entry point test class.
 *
 * @author Eric Dudley
 * @version 3/7/17
 */
public class Museum extends Consoul {

    @Override
    public Action init(ApplicationManager am) {
        am.vm.addColor("menu_text", CharColor.BLACK, CharColor.WHITE);
        am.vm.addColor("highlighted_text", CharColor.WHITE, CharColor.BLACK);
        am.vm.addColor("bg", CharColor.BLACK, CharColor.BLACK);
        am.vm.addColor("error_text", CharColor.RED, CharColor.BLACK);
        Menu menu = (Menu) am.addAction("consoul.actions.Menu", "consoul.views.MenuView");
        menu.setName("Main Menu");
        for (int i = 0; i < 3; i++) {
            Menu newmenu = (Menu) am.addAction("consoul.actions.Menu", "consoul.views.MenuView");
            newmenu.setName("menu option " + i);
            menu.addOption(newmenu);
        }
        Form form = (Form) am.addAction("consoul.actions.Form", "consoul.views.FormView");
        form.setName("Profile Form");
        form.addField("Name");
        form.addField("Age");
        menu.addOption(form);
        return menu;
    }

    public static void main(String[] args) {
        Museum consoul = new Museum();
    }
}
