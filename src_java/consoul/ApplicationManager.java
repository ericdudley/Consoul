package consoul;

import consoul.actions.Action;
import consoul.views.View;
import jcurses.system.InputChar;

import java.util.Queue;

/**
* Entry point for Consoul. Controls application state and
* execution.
*
* @author Eric Dudley
* @version 3/5/17
*/
public class ApplicationManager
{
        private Action current_action;
        private Queue<InputChar> inputQueue;

        public ViewManager vm;
        public ResourceManager rm;

        /**
         * Returns consoul.views.View object for current action.
         *
         * @return Current consoul.views.View for current action.
         */
        public View getView() {
                return null;
        }

        /**
         * Start the application.
         *
         * @return Exit state of application 0 is good.
         */
        public int start() {
                return 0;
        }

        /**
         * Returns input from the inputQueue without blocking.
         *
         * @return Next input as an int.
         */
        public int getInputCodeNoBlock() {
                return 0;
        }

        /**
         * Returns input from the inputQueue without blocking.
         *
         * @return Next input as a char.
         */
        public char getInputCharNoBlock() {
                return 'a';
        }

        /**
         * Returns input from the inputQueue with blocking.
         *
         * @return Next input as an int .
         */
        public int getInputCode() {
                return 0;
        }

        /**
         * Returns input from the inputQueue with blocking.
         *
         * @return Next input as a char.
         */
        public char getInputChar() {
                return 'a';
        }

        /**
         * Called when action's state has changed. Updates view.
         */
        public void notifyChanged() {

        }

        /**
         * Changes current_action to target.
         *
         * @param target consoul.actions.Action to be routed to.
         */
        public void routeTo(Action target) {

        }
}
