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
public class Rectangle extends Shape {
    private double width;
    private double length;
    
    public Rectangle(double width, double length){
        this.width = width;
        this.length = length;
    }
    
    @Override
    public double getPerimeter(){
        return (length + width) * 2;
    }
    
    @Override
    public double getArea(){
        return length * width;
    }
    
}
