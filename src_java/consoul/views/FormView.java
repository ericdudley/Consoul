package consoul.views;
/**
 * Handles view for forms.
 */

import consoul.actions.Form;
import consoul.views.View;

import java.awt.*;

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
        for (int i = 0; i < form.numFields(); i++) {
            vm.color("menu_text");
            if (i == current)
                vm.color("highlighted_text");
            vm.drawString(form.getFields().get(i) + ": " + form.getValues().get(i), fieldsPos.x, y);
            y += 1;
            vm.color("error_text");
            vm.drawString(form.getErrors().get(i), fieldsPos.x, y);
            y += fieldSpacing - 1;
        }
    }

    @Override
    public void calculateSpacing() {
        form = (Form) vm.am.getAction();
        fieldsPos.x = 3;
        fieldsPos.y = 2;
        fieldSpacing = vm.height / form.numFields() >= 3 ? 3 : 2;
    }
}