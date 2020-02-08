/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class ControllerManager {
    
    private Joystick gamepad = new Joystick(0);
    private Joystick joystick = new Joystick(1);
    int speeddiv = 1; //Speed to divide both sides by

    public double[] driveControls(){
        double[] input = new double[2];

        if (gamepad.getRawButtonPressed(5)) { //Toggle speed between fast and slow if left bumper (5) is pressed
            if (speeddiv == 1) {
                speeddiv = 2; //Set slow
            } else {
                speeddiv = 1; //Set fast
            }
        }
        
        //Set inputs
        input[1] = gamepad.getRawAxis(1) / speeddiv;
        input[0] = gamepad.getRawAxis(3) / speeddiv;

        return input;
    }
}
