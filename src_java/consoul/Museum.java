package consoul;

import consoul.actions.*;
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
        Menu menu = (Menu) am.addAction("consoul.actions.Menu", "consoul.views.MenuView");
        menu.setName("Main Menu");
        for (int i = 0; i < 3; i++) {
            Menu newmenu = (Menu) am.addAction("consoul.actions.Menu", "consoul.views.MenuView");
            newmenu.setName("menu option " + i);
            menu.addOption(newmenu);
        }
        Form form = (Form) am.addAction("consoul.actions.Form", "consoul.views.FormView");
        form.setName("Profile Form");
        form.addField(new StringFormField("Name", 20));
        form.addField(new IntFormField("Age"));
        form.addField(new DateFormField("Date"));
        form.addField(new TimeFormField("Time"));
        menu.addOption(form);

        Gallery gallery = (Gallery) am.addAction("consoul.actions.Gallery", "consoul.views.GalleryView");
        gallery.setName("Gallery");
        gallery.addImage(new Image("aaabbbcccdddeee", 3));
        gallery.addImage(new Image("aaaaabbbbbccccc", 5));
        gallery.addImage((new Image("1111222233334444", 4)));
        menu.addOption(gallery);
        return menu;
    }

    public static void main(String[] args) {
        Museum consoul = new Museum();
    }
}
