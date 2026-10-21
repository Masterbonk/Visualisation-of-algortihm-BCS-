package org.algorithm.ui;

import org.algorithm.Color_Algo_State;
import processing.core.PApplet;

import static org.algorithm.Color_Algo_State.*;

public class Color_Scheme {
    PApplet sketch;

    public static int bg;
    public static int bg_button;
    public static int text_button;
    public static int debug_text_button;
    public static int bg_button_hover;
    public static int text_button_hover;
    public static int text_button_clicked;
    public static int bg_button_clicked;
    public static  int border_button;

    //above/below buttons
    public static int hover;
    public static int hover_text;
    public static int hover_stroke;


    public static int weight_button_field_box;
    public static int weight_button_inner_field_box;
    public static int weight_box_stroke;
    public static int weight_box_text_color;


    public static int node_idle;
    public static int line;
    public static int debug_node;
    public static  int cut_node;
    public static  int hover_node;
    public static int in_PQ_node;
    public static int bg_button_algo_zero;
    public static int bg_button_algo_one;
    public static int bg_button_algo_two;
    public static int bg_button_algo_three;
    public static int node_highlighted;

    public static int edge_idle;
    public static int edge_considered;
    public static int edge_final_path;
    public static int edge_delete_hover;
    public static int edge_weight_hover;


    public Color_Scheme(PApplet _sketch){
        sketch = _sketch;
    }
    //bla
    public void changeColors(Color_Scheme_Enum _color_enum, Color_Algo_State _color_algo){
        if (_color_enum == Color_Scheme_Enum.pink_mode){
            bg = sketch.color(226,204,211);
            //make colors pink
            update_Button_Colors(_color_enum);
            update_Node_Colors(_color_enum);
            update_Edge_Colors(_color_enum, _color_algo);
        } else if (_color_enum == Color_Scheme_Enum.base_mode) {
            //default colors
            bg = sketch.color(204);
            update_Button_Colors(_color_enum);
            update_Node_Colors(_color_enum);
            update_Edge_Colors(_color_enum, _color_algo);

            //buttons
        } else if (_color_enum == Color_Scheme_Enum.monotone_mode){
            bg = sketch.color(250,255,255);
            update_Button_Colors(_color_enum);
            update_Node_Colors(_color_enum);
            update_Edge_Colors(_color_enum, _color_algo);
        }
    }

    private void update_Node_Colors(Color_Scheme_Enum _color_enum){
        if (_color_enum == Color_Scheme_Enum.pink_mode){
            node_idle = sketch.color(247,126,196);
            debug_node = sketch.color(181, 3, 252);
            cut_node = sketch.color(119,1,62);
            hover_node = sketch.color(207,99,249);
            in_PQ_node = sketch.color(252,183,200);

            node_highlighted = sketch.color(249, 52, 216);

        } else if (_color_enum == Color_Scheme_Enum.base_mode){
            //default
            node_idle = sketch.color(232,25,25);
            debug_node = sketch.color(181, 3, 252);
            cut_node = sketch.color(160, 4, 4);
            hover_node = sketch.color(24,204,24);
            in_PQ_node = sketch.color(238,218,18);
            node_highlighted = sketch.color(47, 163, 1);

        } else if (_color_enum == Color_Scheme_Enum.monotone_mode){
            node_idle = sketch.color(250,25,25);
            debug_node = sketch.color(181, 3, 252);
            cut_node = sketch.color(160, 4, 4);
            hover_node = sketch.color(253,99,105);
            in_PQ_node = sketch.color(75, 75, 150);
            node_highlighted = sketch.color(47, 163, 1);
        }
    }

    private void  update_Edge_Colors(Color_Scheme_Enum _color_enum, Color_Algo_State _color_algo){

        if (_color_enum == Color_Scheme_Enum.pink_mode) {

            edge_idle = sketch.color(90,70,130);
            edge_considered = sketch.color(150, 122, 247);
            edge_final_path = sketch.color(254, 0, 255);
            edge_delete_hover = sketch.color(146, 40, 62);
            edge_weight_hover = sketch.color(224, 113, 180);

        } else if (_color_enum == Color_Scheme_Enum.base_mode){
            edge_idle = sketch.color(75, 75, 75);
            edge_considered = sketch.color(75, 75, 150);
            edge_final_path = sketch.color(75, 265, 75);
            edge_delete_hover = sketch.color(255, 75, 75);
            edge_weight_hover = sketch.color(150, 75, 75);

        } else if (_color_enum == Color_Scheme_Enum.monotone_mode) {
            edge_idle = sketch.color(110);
            edge_considered = sketch.color(85, 101, 186);
            //edge_final_path = sketch.color(65, 194, 57);
            //edge_delete_hover = sketch.color(255, 75, 75);
            ColorEdgeAlgoConsidered(_color_algo);

        }

    }

    public void ColorEdgeAlgoConsidered(Color_Algo_State _color_Algo){
        switch(_color_Algo){
            case Djikstra:
                edge_considered = sketch.color(85, 101, 186);
                edge_final_path = sketch.color(65, 194, 57);
                break;

            case LPAstar:
                edge_considered = sketch.color(85, 101, 186);
                edge_final_path = sketch.color(65, 194, 57);
                break;

            case DstarLite:
                edge_considered = sketch.color(85, 101, 186);
                edge_final_path = sketch.color(65, 194, 57);
                break;

            case Astar:
                edge_considered = sketch.color(85, 101, 186);
                edge_final_path = sketch.color(65, 194, 57);
                break;
        }
    }

    private void update_Button_Colors(Color_Scheme_Enum _color_enum){
        bg_button_algo_zero = sketch.color(220,220,60); //Yellow
        bg_button_algo_one = sketch.color(40,40,178); //Blue
        bg_button_algo_two = sketch.color(80,220,65); //Green
        bg_button_algo_three = sketch.color(224,60,60); //Red

        if (_color_enum == Color_Scheme_Enum.pink_mode){
            //debug
            debug_text_button = sketch.color(255, 255, 255);

            //hover
            hover = sketch.color(98,51,95);
            hover_text = sketch.color(32,11,46);
            hover_stroke = sketch.color(32,11,46);

            //hover on button
            text_button_hover = sketch.color(201,116,322);
            bg_button_hover = sketch.color(78,31,75);

            //clicked
            text_button_clicked = sketch.color(255,255,255);
            bg_button_clicked = sketch.color(207,99,249);

            //normal 56,28,54
            border_button = sketch.color(140,85,147);
            bg_button = sketch.color(145,50, 129);
            text_button = sketch.color(252,164,237);

            //weight button
            weight_button_field_box = sketch.color(145,50, 129);
            weight_box_stroke = sketch.color(0);
            weight_button_inner_field_box = sketch.color(140,85,147);
            weight_box_text_color = sketch.color(252,164,237);

        } else  if (_color_enum == Color_Scheme_Enum.base_mode) {
            //debug
            debug_text_button = sketch.color(255, 255, 255);

            //hover
            hover = sketch.color(100);
            hover_text = sketch.color(0f);
            hover_stroke = sketch.color(75);

            //hover on button
            text_button_hover = sketch.color(255f);
            bg_button_hover = sketch.color(0f);

            //clicked
            text_button_clicked = sketch.color(255f);
            bg_button_clicked = sketch.color(127,178,96);

            //normal
            border_button = sketch.color(162f,162f,162f);
            bg_button = sketch.color(80f);
            text_button = sketch.color(255f);

            //weight button
            weight_button_field_box = sketch.color(255);
            weight_box_stroke = sketch.color(100);
            weight_button_inner_field_box = sketch.color(100);
            weight_box_text_color = sketch.color(0);

        } else  if (_color_enum == Color_Scheme_Enum.monotone_mode) {
            //debug
            debug_text_button = sketch.color(255, 255, 255);

            //hover
            hover = sketch.color(130);
            hover_text = sketch.color(0f);
            hover_stroke = sketch.color(100);

            //hover on button
            text_button_hover = sketch.color(255f);
            bg_button_hover = sketch.color(50f);

            //clicked
            text_button_clicked = sketch.color(0f);
            bg_button_clicked = sketch.color(132,204,116);

            //normal
            border_button = sketch.color(168f,168f,168f);
            bg_button = sketch.color(204);
            text_button = sketch.color(0f);

            //weight pop up button
            weight_button_field_box = sketch.color(100);
            weight_box_stroke = sketch.color(94);
            weight_button_inner_field_box = sketch.color(150);
            weight_box_text_color = sketch.color(0);

        }
    }
}
