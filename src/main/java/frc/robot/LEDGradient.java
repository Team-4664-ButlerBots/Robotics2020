/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public class LEDGradient {
    private Color[] colorKeys;
    
    public LEDGradient(int keys){
        colorKeys = new Color[keys];
        
        for(int i=0; i<colorKeys.length; i++) {

        }
    }    
    public void setKeyColor(int index, Color color){
        colorKeys[index] = color;
    }
    private Color Interpolate(Color clr1, Color clr2, double g){      // ((light I'm at)/last light)) x (Number of keys) casted to int = lowest key
        Color c = new Color((clr2.red - clr1.red) * g,(clr2.green - clr1.green) * g, (clr2.blue - clr1.blue) * g);
        return c;
    }
    public Color getColor(double input){
        double position = colorKeys.length * input;
        Color color1 = colorKeys[(int)position];   
        Color color2 = colorKeys[(int)position + 1];
        double distance = position - (int)position;   
        Color c = Interpolate(color1,color2,distance);   
        return c;   
    }
}