package consoul;

import consoul.actions.Menu;

/**
 * Entry point test class.
 *
 * @author Eric Dudley
 * @version 3/7/17
 */
public class Museum {
    public static void main(String[] args) {
        ApplicationManager am = new ApplicationManager();
        Menu menu = new Menu(am);
        for (int i = 0; i < 3; i++) {
            Menu newmenu = new Menu(am);
            menu.addOption(newmenu);
        }
        am.start(menu);
    }
}
