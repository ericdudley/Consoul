package consoul.views;

import consoul.actions.Gallery;
import consoul.actions.gallery.Image;

import java.awt.Point;
import java.util.List;

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
        vm.drawString("left for previous", 0, vm.height - 4);
        vm.drawString("right for next", 0, vm.height - 3);
        vm.drawString("i to toggle info", 0, vm.height - 2);
        vm.drawString("backspace to quit", 0, vm.height - 1);
        if(gallery.getImages().size() > 0) {
            Image curr = gallery.getImages().get(gallery.getCurrent());
            img_loc.x = vm.width / 2 - curr.getWidth() / 2;
            if (gallery.showingInfo()) {
                vm.drawString(curr.getInfo(), (vm.width / 2) - curr.getInfo().length() / 2, vm.height / 2);
            } else {
                vm.drawImage(curr, img_loc);
            }
        }
    }

    @Override
    public void calculateSpacing() {
        gallery = (Gallery) vm.am.getAction();
        img_loc.y = 1;
    }
}