package consoul.actions;

/**
 * Created by eric on 3/23/17.
 */
public class FormField {
    private String name;
    private String value;
    private String error;

    public FormField() {
        value = "";
        error = "";
    }

    public FormField(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean validate() {
        return true;
    }
}
