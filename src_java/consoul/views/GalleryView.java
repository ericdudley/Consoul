package consoul.views;

import consoul.actions.Gallery;
import consoul.actions.Image;
import consoul.views.View;

/**
 * view for gallery.
 */

public class GalleryView extends View {
    private Gallery gallery;

    @Override
    public void render() {
        for (Image img : gallery.getImages()) {

        }
    }

    @Override
    public void calculateSpacing() {
        gallery = (Gallery) vm.am.getAction();

    }
}