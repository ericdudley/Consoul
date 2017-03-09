package consoul.views;

import consoul.ViewManager;

/**
* Represents an abstract view to be renderer.
*
* @author Eric Dudley
* @version 3/6/17
*/

public abstract class View
{
        protected ViewManager vm;
        public String title;

    public View() {

    }

    public View(ViewManager _vm) {
        vm = _vm;
    }
        /**
         * Renders the content that the associated action provides.
         */
        public abstract void render();

        /**
         * Used to calculate any absolute coordinate constants that
         * may be needed to render.
         */
        public abstract void calculateSpacing();

    public void setViewManager(ViewManager _vm) {
        vm = _vm;
    }
}

