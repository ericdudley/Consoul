package consoul.actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 3/9/17.
 */
public class ListWidget<E> {
    private List<E> list;
    private List<String> specials;
    private int current;

    public ListWidget() {
        list = new ArrayList<E>();
        specials = new ArrayList<>();
    }

    public int getCurrent() {
        return current;
    }

    public int size() {
        return list.size() + specials.size();
    }

    public void next() {
        current = current == size() - 1 ? 0 : current + 1;
    }

    public void prev() {
        current = current == 0 ? size() - 1 : current - 1;
    }

    public List<E> getList() {
        return list;
    }

    public List<String> getSpecials() {
        return specials;
    }

    public List<String> toStrings() {
        List<String> strlst = new ArrayList<>();
        for (E obj : list) {
            strlst.add(obj.toString());
        }
        for (String spc : specials) {
            strlst.add(spc);
        }
        return strlst;
    }

    public boolean isSpecial(int idx) {
        return idx >= list.size() && idx < size();
    }

    public void addItem(E item) {
        list.add(item);
    }

    public void removeItem(E item) {
        list.remove(item);
    }

    public void addSpecial(String special) {
        specials.add(special);
    }

    public String getSpecial(int index) {
        return specials.get(index - list.size());
    }
}
