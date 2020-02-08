/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Add your docs here.
 */
public class DriveTrain {
    private Spark m_left = new Spark(0);
    private Spark m_right = new Spark(1);
    private DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    public DifferentialDrive getM_drive() {
        return m_drive;
    }
}
