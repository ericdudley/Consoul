package consoul.actions;

import consoul.actions.form.FormField;
import consoul.actions.form.StringFormField;

import java.util.Map;

/**
 * Created by eric on 5/6/17.
 */
public class Search extends Form {
    private String prompt;

    public Search()
    {
        super();
        list.removeSpecial("Save");
        list.removeSpecial("Back");
        list.addSpecial("Search");
        list.addSpecial("Back");

        prompt = "Search";
        StringFormField query = new StringFormField(prompt, 255);
        addField(query);
    }

    public void setPrompt(String p)
    {
        Map<String, FormField> fields = getMap();
        fields.get(prompt).setName(p);
        prompt = p;
    }

    public String getQuery()
    {
        Map<String, FormField> fields = getMap();
        return fields.get(prompt).getValue();
    }

    @Override
    public void reset()
    {
        Map<String, FormField> fields = getMap();
        fields.get(prompt).setValue("");
    }
}
