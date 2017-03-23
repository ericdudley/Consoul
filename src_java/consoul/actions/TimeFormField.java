package consoul.actions;

import static consoul.ResourceManager.dates;

/**
 * Created by eric on 3/23/17.
 */
public class TimeFormField extends FormField {
    public TimeFormField(String name) {
        super(name);
    }

    @Override
    public boolean validate() {
        return this.getValue().matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");
    }
}
