package consoul.views;
/**
 * Handles view for forms.
 */

import consoul.actions.Form;
import consoul.actions.form.FormField;

import java.awt.Point;
import java.util.List;

public class FormView extends View {

    private Point fieldsPos;
    private int fieldSpacing;
    private int errorsOffset;
    private Form form;

    public FormView() {
        fieldsPos = new Point();
    }

    @Override
    public void render() {
        if (form.numFields() == 0) {
            vm.drawString("No fields.", fieldsPos.x, fieldsPos.y);
            return;
        }
        int y = fieldsPos.y;
        int current = form.getCurrent();
        List<FormField> fields = form.getFields();
        for (int i = 0; i < fields.size(); i++) {
            vm.color("menu_text");
            if (i == current)
                vm.color("highlighted_text");
            vm.drawString(fields.get(i).getName() + ": " + fields.get(i).getValue(), fieldsPos.x, y);
            vm.color("error_text");
            vm.drawString(fields.get(i).getError(), fieldsPos.x, y + 1);
            y += fieldSpacing;
        }
        List<String> specials = form.getSpecials();
        for (int i = 0; i < specials.size(); i++) {
            vm.color("menu_text");
            if (i + fields.size() == current)
                vm.color("highlighted_text");
            vm.drawString(specials.get(i), fieldsPos.x, y);
            y += fieldSpacing;
        }
    }

    @Override
    public void calculateSpacing() {
        form = (Form) vm.am.getAction();
        fieldsPos.x = 3;
        fieldsPos.y = 2;
        fieldSpacing = vm.height / form.numFields() >= 3 ? 4 : 3;
    }
}