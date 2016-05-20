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
public class Triangle extends Shape {
    
    double side1, side2, side3;

    @Override
    public double getArea() {
        double halfPerimeter = getPerimeter()/2;
        return Math.sqrt((halfPerimeter*(halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3)));
        
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }
    
}
