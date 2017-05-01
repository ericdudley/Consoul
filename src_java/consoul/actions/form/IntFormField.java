package consoul.actions.form;

/**
 * Created by eric on 3/23/17.
 */
public class IntFormField extends FormField {

    public IntFormField(String name) {
        super(name);
    }

    @Override
    public boolean validate() {
        return this.getValue().matches("^-?\\d+$");
    }

    public int getInt()
    {
        return Integer.parseInt(this.getValue());
    }
}
