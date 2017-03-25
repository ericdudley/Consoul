package consoul.actions.form;

import static consoul.ResourceManager.dates;

/**
 * Created by eric on 3/23/17.
 */
public class DateFormField extends FormField {
    public DateFormField(String name) {
        super(name);
    }

    @Override
    public boolean validate() {
        return dates.contains(this.getValue());
    }
}
