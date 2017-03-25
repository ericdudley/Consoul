package consoul.tools;

import consoul.ApplicationManager;
import jcurses.system.CharColor;
import jcurses.system.InputChar;

import static jcurses.system.Toolkit.printString;
import static jcurses.system.Toolkit.readCharacter;

/**
* Continuously gets input from user and stores it
* in application manager.
*
* @author Eric Dudley
* @version 3/5/17
*/

public class InputThread extends Thread
{
        private ApplicationManager am;
    private Object input_sync;


    public InputThread(ApplicationManager _am, Object _input_sync) {
        this.am = _am;
        this.input_sync = _input_sync;
    }

        /**
         * Continuously prompts for input from Jcurses.
         * Sends input to am.
         */
        public void run() {
            while (am.alive) {
                InputChar inchar = readCharacter();
                printString(Integer.toString(inchar.getCode()), 0, 0, new CharColor(CharColor.WHITE, CharColor.BLUE));
                am.addInput(inchar);
                synchronized (input_sync) {
                    input_sync.notify();
                }
            }
            am.vm.color("error_text");
            am.vm.drawString("Press any key to exit...", 0, 0);
        }
}
