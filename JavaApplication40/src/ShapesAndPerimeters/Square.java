/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShapesAndPerimeters;

/**
 *
 * @author apprentice
 */
public class Square extends Shape {
    private double side;
    
    public Square(double side){
        this.side = side;
    }
    
    @Override
    public double getPerimeter(){
        return side * 4;
    }
    
    @Override
    public double getArea(){
        return side * side;
    }
}
