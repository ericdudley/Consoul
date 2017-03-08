package consoul;

import consoul.views.MenuView;
import jcurses.system.CharColor;
import consoul.views.View;

import java.awt.*;
import java.util.Map;

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
        }

        /**
         * Draws anything before the view.
         * Such as a border.
         */
        private void preRender() {

        }

        /**
         * Called when the view should be redrawn.
         */
        public void update() {
                preRender();
                curr_view = new MenuView(this);
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
         * @param colo1 Foreground color.
         * @param colo2 Background color.
         */
        public void addColor(String colo1, String colo2) {

        }

        /**
         * Sets color to color.
         *
         * @param key Color name in colors map.
         */
        public void color(String key) {
                curr_color = new CharColor(CharColor.WHITE, CharColor.GREEN);
        }
}
