package consoul.actions.form;

import java.sql.Time;

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

    public Time getTime()
    {
        return Time.valueOf(getValue()+":00");
    }
}
