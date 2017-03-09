package consoul.actions;

import consoul.ApplicationManager;

/**
* Abstract class for consoul.actions in Consoul.
*
* @author Eric Dudley
* @version 3/5/17
*/
public abstract class Action
{
        protected ApplicationManager am;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name;

    public Action() {
        name = "Default Action";
    }

    public Action(ApplicationManager _am) {
        am = _am;
    }

        /**
        * Main execution method for action.
        */
        public abstract void execute();

        /**
        * Preloads any required data before executing.
        */
        public void preLoad(){}

        /**
        * Resets action to initial state.
        */
        public abstract void reset();

        /**
        * Saves action's state to resource manager.
        */
        public void save(){}

        /**
        * Performs any close down routines.
        */
        public void close(){}

    public void setApplicationManager(ApplicationManager _am) {
        am = _am;
    }

        /**
         * Name
         */
        @Override
        public String toString() {
            return name;
        }
}
