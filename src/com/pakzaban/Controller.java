package com.pakzaban;

import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Controller {
    public AnchorPane anchorPane;

    public Pane graphPane;
    public Slider slider;
    private double startX;
    private double startY;

    public void mouseMoved(MouseEvent me) {
        double xBorder = (anchorPane.getWidth()-graphPane.getWidth())/2;
        double yBorder = (anchorPane.getHeight() - graphPane.getHeight())/2;
        double x = me.getSceneX() - xBorder;
        double y = me.getSceneY() - yBorder;
        System.out.println("X = " + x + " Y = " + y);
        if (me.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
//            drawRectangle(x, y);
            startX = x;
            startY = y;
        }
        else if (me.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
//            enlargeRectangle(x,y);
        }
        else if (me.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
//            drawRectangle(x,y);
            Rectangle r = new Rectangle(startX,startY, x-startX,y-startY);
            r.setFill(Paint.valueOf(colorize()));
            r.setStroke(null);
            graphPane.getChildren().add(r);
        }
    }

    public String colorize(){
        String colorString = "RED,YELLOW,PURPLE,BLUE,SALMON,MAGENTA,HOTPINK,GOLDENROD,PINK,TURQUOISE,SKYBLUE";
        String[] colorArray = colorString.split(",");
        String color = colorArray [(int) (Math.random() * colorArray.length)];
        return color;
    }

    public void drawRectangle (double x, double y){
        Polygon p = new Polygon(x-10,y-10,x+10,y-10,x+10,y+10,x-10,y+10);
        p.setStyle("-fx-fill: white; -fx-stroke: black");
        graphPane.getChildren().clear();
        graphPane.getChildren().add(p);
    }

    public void enlargeRectangle (double x, double y){
        Polygon p = new Polygon(x-10,y-10,x+10,y-10,x+10,y+10,x-10,y+10);
        p.setStyle("-fx-fill: none; -fx-stroke: black");
        graphPane.getChildren().clear();
        graphPane.getChildren().add(p);
        ScaleTransition st = new ScaleTransition();
        st.setNode(p);
        st.setDuration(Duration.seconds(0.5));
        st.setByY(-1);
        st.setByX(-1);
        st.play();
        System.out.println(x);
    }


    public void clear(){
        graphPane.getChildren().clear();
        slider.adjustValue(0.0);
    }
}

