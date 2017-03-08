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
        private Action curr_action;
        private Queue<InputChar> inputQueue;

        public ViewManager vm;
        public ResourceManager rm;

        /**
         * Constructor. Creates vm.
         */
        public ApplicationManager() {
                vm = new ViewManager(this);
        }

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
         * @param action Entry point action.
         * @return Exit state of application 0 is good.
         */
        public int start(Action action) {
                routeTo(action);
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
                vm.update();
        }

        /**
         * Changes curr_action to target.
         *
         * @param target consoul.actions.Action to be routed to.
         */
        public void routeTo(Action target) {
                curr_action = target;
                curr_action.preLoad();
                curr_action.execute();
        }

        /**
         * Getter.
         *
         * @return Current action.
         */
        public Action getAction() {
                return this.curr_action;
        }
}
