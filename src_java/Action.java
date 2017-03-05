/**
* Abstract class for actions in Consoul.
*
* @author Eric Dudley
* @version 3/5/17
*/
public abstract class Action
{
        protected ApplicationManager am;

        /**
        * Main execution method for action.
        */
        public abstract void execute();

        /**
        * Preloads any required data before executing.
        */
        public void preLoad();

        /**
        * Resets action to initial state.
        */
        public void reset();

        /**
        * Saves action's state to resource manager.
        */
        public void save();

        /**
        * Performs any close down routines.
        */
        public void close();

        /**
        * Handles globally recognized input handling.
        */
        public void defaultInputHandler();
}
