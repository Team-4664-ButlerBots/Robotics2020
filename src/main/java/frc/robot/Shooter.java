/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;

/**
 * Add your docs here.
 */
public class Shooter {
    private Spark arm = new Spark(2);
    private Victor LeftShoot = new Victor(3);
    private Victor RightShoot = new Victor(4);
    
    private ControllerManager cManager;

    public Shooter(ControllerManager cManager){
        this.cManager = cManager;
    }

    public void OperatorControl(){
        arm.set(cManager.getArmInput());
        setSpeed(cManager.getShootSpeed());

    }
    private void setSpeed(double speed){
        LeftShoot.set(speed);
        RightShoot.set(speed);
    }
}
