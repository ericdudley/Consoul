package consoul.views;

/**
 * Created by eric on 3/9/17.
 */
public class ErrorView extends View {
    @Override
    public void render() {
        vm.drawString("ERROR OCCURRED", 0, 0);
    }

    @Override
    public void calculateSpacing() {

    }
}
