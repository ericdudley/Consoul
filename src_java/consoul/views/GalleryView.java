package consoul.views;

import consoul.actions.Gallery;
import consoul.actions.Image;
import consoul.views.View;

import java.awt.Point;

/**
 * view for gallery.
 */

public class GalleryView extends View {
    private Gallery gallery;
    private Point img_loc;

    public GalleryView() {
        img_loc = new Point();
    }

    @Override
    public void render() {
        vm.color("menu_text");
        String str = (gallery.getCurrent() + 1) + " of " + gallery.getImages().size();
        vm.drawString(str, vm.width - 1 - str.length(), vm.height - 1);
        vm.drawImage(gallery.getImages().get(gallery.getCurrent()), img_loc);
        vm.drawString("left for previous", 0, vm.height - 4);
        vm.drawString("right for next", 0, vm.height - 3);
        vm.drawString("i to toggle info", 0, vm.height - 2);
        vm.drawString("backspace to quit", 0, vm.height - 1);
    }

    @Override
    public void calculateSpacing() {
        gallery = (Gallery) vm.am.getAction();
        img_loc.y = 1;
        int img_width = gallery.getImages().get(gallery.getCurrent()).getWidth();
        img_loc.x = (vm.width - img_width) / 2;
    }
}