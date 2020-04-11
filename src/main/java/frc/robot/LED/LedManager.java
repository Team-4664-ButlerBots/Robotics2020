/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.LED;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.ControllerManager;
import frc.robot.LED.ColorLookUpTable.AnimationType;
import frc.robot.LED.ColorLookUpTable.InterpolationType;

/**
 * Add your docs here. this class handles different led animation states and
 * networking
 */
public class LedManager {
    private NetworkTableInstance ntist = NetworkTableInstance.getDefault();
    private NetworkTable ledTable;
    private NetworkTableEntry nBrightness, nSpeed;

    private ColorLookUpTable test = new ColorLookUpTable(4);
    private LEDstrip frontLED = new LEDstrip(9, 8);

    ControllerManager cManager;
    public LedManager(ControllerManager cManager){
        NetSetup();
        this.cManager = cManager;
        test.SetInterpolationType(InterpolationType.closest);
    }

    public void NetSetup(){
        ledTable = ntist.getTable("led");
        // instantiate the values
        nBrightness = ledTable.getEntry("brightness");
        nSpeed = ledTable.getEntry("speed");
        // attempt to grab existing values from network tables otherwise use default values
        nBrightness.setDouble(nBrightness.getDouble(1));
        nSpeed.setDouble(nSpeed.getDouble(1));
    }

    public void DisabledUpdate(){
        test.setGrid(Color.kFirstRed, Color.kBlueViolet);
        test.animate(AnimationType.pulse, 0.05);
    }

    public void TeleopUpdate(){
        test.setBrightness(1);
        test.setGrid(Color.kBlack, Color.kAliceBlue);
        test.animate(AnimationType.addOffset, (cManager.getDriveInput()[0] + cManager.getDriveInput()[1]) * 0.025);
    }

    public void PeriodicUpdate(){
        frontLED.mapLookupTable(test);
    }


}
