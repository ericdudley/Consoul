package consoul;

import consoul.actions.Action;

import static java.lang.Thread.sleep;

/**
 * Base class for a Consoul application.
 * Created by eric on 3/9/17.
 */
public abstract class Consoul {

    private ApplicationManager am;

    public Consoul() {
        am = new ApplicationManager();
        Action act = init(am);
        start(act);
    }

    /**
     * Must be overridden by application class.
     *
     * @return Entry point for application.
     */
    public abstract Action init(ApplicationManager am);

    /**
     * Starts application.
     *
     * @param entry Entry point action.
     */
    public void start(Action entry) {
        am.start(entry);
    }
}
