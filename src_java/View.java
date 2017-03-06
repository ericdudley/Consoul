/**
* Represents an abstract view to be renderer.
*
* @author Eric Dudley
* @version 3/6/17
*/

public abstract class View
{
        private ViewManager vm;
        public String title;

        /**
        * Renders the content that the associated action provides.
        */
        public abstract void render();

        /**
        * Used to calculate any absolute coordinate constants that
        * may be needed to render.
        */
        public abstract void calculateSpacing();
}
