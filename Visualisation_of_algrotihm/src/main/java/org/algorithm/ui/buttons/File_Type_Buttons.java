package org.algorithm.ui.buttons;

import org.algorithm.ui.Color_Scheme;
import processing.core.PApplet;

import static org.algorithm.Main.Ui; /**
 * Unique type knap til knapper under file, som skal gemmes væk når man ikke har klikket på file.
 */

public class File_Type_Buttons extends Button {

    public File_Type_Buttons(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    public void render(){
        if (Ui.get_Button("file").clicked){
            sketch.fill(Color_Scheme.border_button);
            sketch.rect(x_pos,y_pos, x_size, y_size);

            int changeVal = 5;

            if (mouse_Over()){
                sketch.push();
                sketch.fill(Color_Scheme.bg_button_hover);
                sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
                sketch.fill(Color_Scheme.text_button_hover); //Text color
                sketch.textSize((x_size-changeVal)/5f);
                sketch.textAlign(sketch.CENTER);
                sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2f, (y_pos+changeVal)+(y_size-changeVal*2)/2f+10);
                sketch.pop();
            } else {
                sketch.push();
                sketch.fill(Color_Scheme.bg_button);
                sketch.rect(x_pos,y_pos, x_size, y_size);
                sketch.fill(Color_Scheme.text_button);
                sketch.textSize(x_size/5f);
                sketch.textAlign(sketch.CENTER);
                sketch.text(text, (x_pos)+(x_size)/2f, (y_pos)+(y_size)/2f+10);
                sketch.pop();
            }
        }
    }

    @Override
    public boolean mouse_Over() {
        if (Ui.get_Button("file").clicked) {
            return super.mouse_Over();
        } else {
            return false;
        }
    }
}
