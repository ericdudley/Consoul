package consoul;

import consoul.actions.Action;
import consoul.tools.InputThread;
import jcurses.system.InputChar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Thread.sleep;

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

        public List<Action> title_actions;
        private Action curr_action;
        public boolean alive;
    private Queue<InputChar> input_queue;
    private Object input_sync;

        public ViewManager vm;
        public ResourceManager rm;
        public InputThread it;

        /**
         * Constructor. Creates vm.
         */
        public ApplicationManager() {
            vm = new ViewManager(this);
            rm = new ResourceManager(this);
            input_sync = new Object();
            it = new InputThread(this, input_sync);
            input_queue = new LinkedList<>();
            alive = true;
            title_actions = new ArrayList<>();
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

    public void addAction(Action act, String vie) {
        act.setApplicationManager(this);
        vm.addActionView(act, vie);
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
            input_queue.add(in);
        }

        /**
         * Handles default input.
         */
        public void defaultInputHandler(InputChar inc) {
        }
        /**
         * Returns input from the input_queue without blocking.
         *
         * @return Next input as an int.
         */
        public int getInputCodeNoBlock() {
            if (input_queue.size() > 0) {
                InputChar inc = input_queue.remove();
                        defaultInputHandler(inc);
                        return inc.getCode();
                }
                return INVALID_INT;
        }

        /**
         * Returns input from the input_queue without blocking.
         *
         * @return Next input as a char.
         */
        public char getInputCharNoBlock() {
            if (input_queue.size() > 0) {
                InputChar inc = input_queue.remove();
                        defaultInputHandler(inc);
                        return inc.getCharacter();
                }
                return INVALID_CHAR;
        }

        /**
         * Returns input from the input_queue with blocking.
         *
         * @return Next input as an int .
         */
        public int getInputCode() {
            blockForInput();
            InputChar inc = input_queue.remove();
                defaultInputHandler(inc);
                return inc.getCode();
        }

        /**
         * Returns input from the input_queue with blocking.
         *
         * @return Next input as a char.
         */
        public char getInputChar() {
            blockForInput();
            InputChar inc = input_queue.remove();
                defaultInputHandler(inc);
                return inc.getCharacter();
        }

        public InputChar getInput() {
            blockForInput();
            InputChar inc = input_queue.remove();
            defaultInputHandler(inc);
            return inc;
        }

    /**
     * Returns input from the input_queue without blocking.
     *
     * @return Next input as a InputChar.
     */
    public InputChar getInputNoBlock() {
        if (input_queue.size() > 0) {
            InputChar inc = input_queue.remove();
            defaultInputHandler(inc);
            return inc;
        }
        return null;
    }

    private void blockForInput() {
        synchronized (input_sync) {
            while (input_queue.size() <= 0) {
                try {
                    input_sync.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                        }
                }
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
                title_actions.add(target);
                vm.preRender();
                curr_action.preLoad();
                vm.update();
                curr_action.execute();
                title_actions.remove(curr_action);
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
