package consoul.actions;

/**
 * Created by eric on 3/9/17.
 */
public class Image {
    private final String value;
    private int width;
    private int height;

    public Image(String value) {
        this(value, value.length());
    }

    public Image(String value, int width) {
        this.value = value;
        setWidth(width);
    }


    public String[] toRows() {
        String[] rows = new String[height];
        for (int i = 0; i < height; i++) {
            rows[i] = value.substring(i * width, (i + 1) * width);
        }
        return rows;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        this.height = value.length() / width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        this.width = value.length() / height;
    }

    public static void main(String[] args) {
        Image img = new Image("aaaaabbbbbcccccddddd");
        img.setWidth(4);
        img.toRows();
    }
}
