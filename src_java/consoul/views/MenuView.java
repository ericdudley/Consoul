package consoul.views;

import consoul.ViewManager;
import consoul.actions.Action;
import consoul.actions.Menu;

import static java.lang.Integer.min;

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
    private int curr_start;
    private int curr_end;
    private int max_options;
    private Menu menu;

    public MenuView()
    {
        super();
        curr_start = 0;
    }

    @Override
    public void render() {
        vm.color("title_text");
        vm.drawString(menu.getName() + " ("+numOptions+" options)", leftMargin, topMargin - 1);
        vm.color("highlighted_text");
        vm.drawString(""+curr_start, leftMargin, topMargin);
        if(menu.getCurrent() < curr_start){
            curr_start -= curr_start - menu.getCurrent();
        }
        else if(menu.getCurrent() > curr_start + max_options - 1){
            curr_start += menu.getCurrent() - (curr_start + max_options - 1);
        }
        int y = topMargin + 1;
        for (int i = curr_start; i < menu.getOptions().size() && i < curr_start + max_options; i++) {
            vm.color("menu_text");
            if (i == menu.getCurrent())
                vm.color("highlighted_text");
            vm.drawString(menu.getOptions().get(i).toString(), leftMargin + 1, y);
            y += lineSpacing;
        }
        vm.color("highlighted_text");
        vm.drawString(""+min(curr_end, numOptions), leftMargin, y-1);
    }

    @Override
    public void calculateSpacing() {
        leftMargin = 5;
        topMargin = 5;
        menu = (Menu) vm.am.getAction();
        numOptions = menu.getOptions().size();
        lineSpacing = 2;
        max_options = (vm.height - topMargin*2) / lineSpacing;
        curr_end = curr_start + max_options - 1;
    }
}
