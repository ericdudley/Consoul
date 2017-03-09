package consoul;

import consoul.actions.Action;
import consoul.views.View;
import jcurses.system.InputChar;
import jdk.internal.util.xml.impl.Input;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;
import static jcurses.system.Toolkit.clearScreen;
import static jcurses.system.Toolkit.shutdown;

/**
* Entry point for Consoul. Controls application state and
* execution.
*
* @author Eric Dudley
* @version 3/5/17
*/
public class ApplicationManager
{
        public static final char INVALID_CHAR = '`';
        public static final int INVALID_INT = -1;
        public static final int POLLING_DELAY = 100;

        private Action curr_action;
        public boolean alive;
        private Queue<InputChar> inputQueue;

        public ViewManager vm;
        public ResourceManager rm;
        public InputThread it;

        /**
         * Constructor. Creates vm.
         */
        public ApplicationManager() {
                vm = new ViewManager(this);
                rm = new ResourceManager(this);
                it = new InputThread(this);
                inputQueue = new LinkedList<>();
                alive = true;
        }

        /**
         * Adds action.
         */
        public Action addAction(String act, String vie) {
                try {
                        Class cls = Class.forName(act);
                        Action action = (Action) cls.newInstance();
                        action.setApplicationManager(this);
                        vm.addActionView(action, vie);
                        return action;
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                } catch (InstantiationException e) {
                        e.printStackTrace();
                }
                return null;
        }

        /**
         * Start the application.
         *
         * @param action Entry point action.
         * @return Exit state of application 0 is good.
         */
        public int start(Action action) {
                it.start();
                routeTo(action);
                alive = false;
                return 0;
        }

        /**
         * Adds inputchar to queue.
         *
         * @param in
         */
        public void addInput(InputChar in) {
                inputQueue.add(in);
        }

        /**
         * Handles default input.
         */
        public void defaultInputHandler(InputChar inc) {
                if (!inc.isSpecialCode() && inc.getCharacter() == 'q') {
                        System.exit(0);
                }
        }
        /**
         * Returns input from the inputQueue without blocking.
         *
         * @return Next input as an int.
         */
        public int getInputCodeNoBlock() {
                if(inputQueue.size() > 0) {
                        InputChar inc = inputQueue.remove();
                        defaultInputHandler(inc);
                        return inc.getCode();
                }
                return INVALID_INT;
        }

        /**
         * Returns input from the inputQueue without blocking.
         *
         * @return Next input as a char.
         */
        public char getInputCharNoBlock() {
                if(inputQueue.size() > 0) {
                        InputChar inc = inputQueue.remove();
                        defaultInputHandler(inc);
                        return inc.getCharacter();
                }
                return INVALID_CHAR;
        }

        /**
         * Returns input from the inputQueue with blocking.
         *
         * @return Next input as an int .
         */
        public int getInputCode() {
                while (inputQueue.size() <= 0) {
                        try {
                                sleep(POLLING_DELAY);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
                InputChar inc = inputQueue.remove();
                defaultInputHandler(inc);
                return inc.getCode();
        }

        /**
         * Returns input from the inputQueue with blocking.
         *
         * @return Next input as a char.
         */
        public char getInputChar() {
                while(inputQueue.size() <= 0) {
                        try {
                                sleep(POLLING_DELAY);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
                InputChar inc = inputQueue.remove();
                defaultInputHandler(inc);
                return inc.getCharacter();
        }

        public InputChar getInput() {
                while (inputQueue.size() <= 0) {
                        try {
                                sleep(POLLING_DELAY);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
                InputChar inc = inputQueue.remove();
                defaultInputHandler(inc);
                return inc;
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
                Action prev = curr_action;
                curr_action = target;
                vm.update();
                curr_action.preLoad();
                curr_action.execute();
                vm.preRender();
                if (prev != null) {
                        prev.reset();
                        curr_action = prev;
                }
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
