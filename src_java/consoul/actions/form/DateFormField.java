package consoul.actions.form;

import java.sql.Date;
import java.util.Calendar;

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

    public Date getDate()
    {
        String[] myd = this.getValue().split("/");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(myd[2]));
        cal.set(Calendar.MONTH, Integer.parseInt(myd[0]));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(myd[1]));
        return new Date(cal.getTimeInMillis());
    }
}
