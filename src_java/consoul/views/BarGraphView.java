package consoul.views;

import consoul.actions.BarGraph;

import java.awt.*;
import java.util.Map;

/**
 * Created by eric on 5/2/17.
 */
public class BarGraphView extends View {
    private Point fieldsPos;
    private int fieldSpacing;
    BarGraph bg;

    public BarGraphView()
    {
        fieldsPos = new Point();
    }

    @Override
    public void render() {
        int y = fieldsPos.y;
        int max = Integer.MIN_VALUE;
        Map<String, Integer> vals = bg.getVals();
        for(String s: vals.keySet()){
            if(vals.get(s) > max){
                max = vals.get(s);
            }
        }
        for(String s: vals.keySet()){
            int size = vals.get(s);
            float ratio = (float)size / max;
            StringBuilder sb = new StringBuilder(vm.width);
            for(int i=0; i<ratio*vm.width*0.9; i++){
                sb.append((char)219);
            }
            vm.color("title_text");
            vm.drawString(s, fieldsPos.x, y);
            vm.color("green_text");
            vm.drawString(sb.toString(), fieldsPos.x, y+1);
            y+=fieldSpacing;
        }
    }

    @Override
    public void calculateSpacing() {
        bg = (BarGraph)vm.am.getAction();
        fieldsPos.x = 3;
        fieldsPos.y = 2;
        fieldSpacing = 3;
    }
}
