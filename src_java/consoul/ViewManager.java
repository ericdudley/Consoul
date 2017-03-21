package consoul;

import consoul.actions.Action;
import consoul.views.MenuView;
import jcurses.system.CharColor;
import consoul.views.View;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static jcurses.system.Toolkit.clearScreen;
import static jcurses.system.Toolkit.printString;

/**
* Manages the view for Consoul.
*/
public class ViewManager
{
        private Map<String,CharColor> colors;
        private CharColor curr_color;
        private Point viewport_TL;
        private Point viewport_BR;
        private Map<Action, String> view_map;

        public ApplicationManager am;
        public View curr_view;
        public Boolean draw_border;
        public int height;
        public int width;


        /**
         * Initializes am.
         */
        public ViewManager(ApplicationManager _am) {
                am = _am;
                colors = new HashMap<>();
                view_map = new HashMap<>();

                addColor("menu_text", CharColor.BLACK, CharColor.WHITE);
                addColor("highlighted_text", CharColor.WHITE, CharColor.BLACK);
                addColor("bg", CharColor.BLACK, CharColor.BLACK);
                addColor("error_text", CharColor.RED, CharColor.BLACK);
        }

        /**
         * Adds to map.
         *
         * @param action
         * @param view
         */
        public void addActionView(Action action, String view) {
                view_map.put(action, view);
        }

        /**
         * Draws anything before the view.
         * Such as a border.
         */
        public void preRender() {
                color("bg");
                clearScreen(curr_color);
        }

        /**
         * Used for safely accessing map.
         */
        public String getView(Action action) {
                if (view_map.containsKey(action)) {
                        return view_map.get(action);
                } else {
                        return "ErrorView";
                }
        }

        /**
         * Changes view object if needed.
         */
        public void changeView() {
                Action action = am.getAction();
                try {
                        Class newview = Class.forName(getView(action));
                        curr_view = (View) newview.newInstance();
                        curr_view.setViewManager(this);
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                } catch (InstantiationException e) {
                        e.printStackTrace();
                }
        }

        /**
         * Called when the view should be redrawn.
         */
        public void update() {
                Action action = am.getAction();
                if (curr_view == null || !curr_view.getClass().toString().equals(getView(action)))
                        changeView();
                preRender();
                curr_view.calculateSpacing();
                curr_view.render();
        }

        /**
         * Draws a string to the screen with viewport
         * adjusted coordinates.
         *
         * @param str Printed String
         * @param x x coordinate
         * @param y y coordinate
         */
        public void drawString(String str, int x, int y) {
                printString(str, x, y, curr_color);
        }

        /**
         * Draws a string to the screen with viewport
         * adjusted coordinates.
         *
         * @param str Printed String
         * @param point Point of first char.
         */
        public void drawString(String str, Point point) {

        }

        /**
         * Adds a color to the colors map.
         *
         * @param name Name of color.
         * @param colo1 Foreground color.
         * @param colo2 Background color.
         */
        public void addColor(String name, short colo1, short colo2) {
                colors.put(name, new CharColor(colo1, colo2));
        }

        /**
         * Sets color to color.
         *
         * @param key Color name in colors map.
         */
        public void color(String key) {
                if (colors.containsKey(key))
                        curr_color = colors.get(key);
                else
                        curr_color = new CharColor(CharColor.RED, CharColor.RED);
        }
}
