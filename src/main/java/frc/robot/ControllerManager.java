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
    //public Joystick joystick = new Joystick(1);

    private boolean speedToggled = false;
    
    public boolean speedToggle(){
        if (gamepad.getRawButtonPressed(5)) { //Toggle speed between fast and slow if left bumper (5) is pressed
            if (speedToggled)
                speedToggled = false;
            else
                speedToggled = true;

        }
        return speedToggled;
    }

    public double[] getDriveInput(){
        double[] input = new double[2];
        
        
        
        //Set inputs
        input[0] = gamepad.getRawAxis(3);
        input[1] = gamepad.getRawAxis(1);
        
        return input;
    }

    public double collectorInput(){
        if( gamepad.getRawButton(2)){
            return 1;
        }else{
            return 0;
        }
    }
}
