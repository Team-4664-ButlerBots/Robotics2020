/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.*;

/**
 * Add your docs here.
 */
public class Vision {

    private DriveTrain dTrain;
    private NetworkTableInstance ntist = NetworkTableInstance.getDefault();
    private NetworkTable visionTable;
    private NetworkTableEntry xCenter, yCenter, RectSize, ContourState, kp, ki, kd;
    PIDController pid = new PIDController(0, 0, 0);


    public Vision(DriveTrain dTrain){
        this.dTrain = dTrain;
        visionTable = ntist.getTable("vision");
        xCenter = visionTable.getEntry("Xposition");
        yCenter = visionTable.getEntry("Yposition");
        RectSize = visionTable.getEntry("Size");
        ContourState = visionTable.getEntry("NoTarget"); 
        kp = visionTable.getEntry("kp");
        ki = visionTable.getEntry("ki");
        ki = visionTable.getEntry("kd");
        pid.setPID(kp.getDouble(0), ki.getDouble(0), kd.getDouble(0));
    }

    public void trackTarget(){
        //set robot to turn to face target from published xPosition from raspberry pi;
        dTrain.getDiffDrive().arcadeDrive( 0 , pid.calculate(0.5, xCenter.getDouble(0.5)));
    }
}
