package consoul.views;

import consoul.ViewManager;
import consoul.actions.Action;
import consoul.actions.Menu;

/**
 * View for Menu action.
 *
 * @author Eric Dudley
 * @version 3/6/17
 */

public class MenuView extends View {
    private int leftMargin;
    private int topMargin;
    private int lineSpacing;
    private int numOptions;
    private Menu menu;

    @Override
    public void render() {
        vm.color("title_text");
        vm.drawString(menu.getName(), leftMargin, topMargin - 1);
        int y = topMargin + 1;
        for (int i = 0; i < menu.getOptions().size(); i++) {
            vm.color("menu_text");
            if (i == menu.getCurrent())
                vm.color("highlighted_text");
            vm.drawString(menu.getOptions().get(i).toString(), leftMargin + 1, y);
            y += lineSpacing;
        }
    }

    @Override
    public void calculateSpacing() {
        leftMargin = 5;
        topMargin = 5;
        menu = (Menu) vm.am.getAction();
        numOptions = menu.getOptions().size();
        lineSpacing = vm.height / numOptions >= 3 ? 3 : 2;
    }
}
