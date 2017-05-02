package consoul.actions;

import consoul.actions.form.FormField;

/**
 * Created by eric on 5/2/17.
 */
public class Info extends Form {
    public Info()
    {
        super();
        list.removeSpecial("Save");
    }


    @Override
    protected void addToField(int index, char ch) {
    }

    @Override
    protected void removeFromField(int index) {
    }
}
