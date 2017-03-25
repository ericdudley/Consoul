package consoul.actions.form;

/**
 * Created by eric on 3/23/17.
 */
public class StringFormField extends FormField {

    private int max_length;

    public StringFormField(String name, int max_length) {
        super(name);
        this.max_length = max_length;
    }

    @Override
    public boolean validate() {
        return this.getValue().length() <= max_length;
    }
}
