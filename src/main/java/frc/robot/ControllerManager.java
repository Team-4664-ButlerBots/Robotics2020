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
    
    public double[] driveControlls(){
        double[] input = new double[2];
        
        input[0] = gamepad.getRawAxis(1);
        input[0] = gamepad.getRawAxis(3);

        return input;
    }
}
