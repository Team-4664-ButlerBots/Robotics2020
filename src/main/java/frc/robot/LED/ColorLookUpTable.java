/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.LED;

import edu.wpi.first.wpilibj.util.Color;

public class ColorLookUpTable {
    private Color[] keys;
    private double offset = 0;

    public enum InterpolationType {
        linear, closest
    }
    private InterpolationType interType = InterpolationType.linear;

    public enum AnimationType {
        addOffset, subOffest
    }

    public ColorLookUpTable(int keyAmt) {
        keys = new Color[keyAmt];
    }

    public void setKey(int index, Color color) {
        keys[index] = color;
    }

    public void SetInterpolationType(InterpolationType type){
        interType = type;
    }

    private Color Interpolate(Color clr1, Color clr2, double g) { // ((light I'm at)/last light)) x (Number of keys)
                                                                  // casted to int = lowest key
        switch (interType) {
        case linear:
            Color c = new Color((clr2.red - clr1.red) * g + clr1.red, (clr2.green - clr1.green) * g + clr1.green,
                    (clr2.blue - clr1.blue) * g + clr1.blue);
            return c;
        case closest:
            if(g < 0.5){
                return clr1;
            }else{
                return clr2;
            }

        default:
            System.out.println("Error=====Invalid Interpolation Type======Error");
            return new Color(0, 0, 255);
        }

    }

    public Color getColor(double input) {
        input += offset;
        input = input - (int) input;
        double position = keys.length * input;
        Color color1 = keys[(int) position];

        Color color2;
        // check to see if the next color wraps around to index 0
        if (((int) position + 1) > keys.length - 1) {
            color2 = keys[0];
        } else {
            color2 = keys[(int) position + 1];
        }
        double distance = position - (int) position;
        Color c = Interpolate(color1, color2, distance);
        return c;
    }

    public void animate(AnimationType type, double speed) {
        switch (type) {
        case addOffset:
            offset += speed;
            break;

        default:
            break;
        }
    }

    /**
     * alternates keys between two colors
     */
    public void setGrid(Color col1, Color col2) {
        for (int i = 0; i < keys.length; i++) {
            if ((i % 2) == 0)
                keys[i] = col1;
            else
                keys[i] = col2;
        }
    }

}