/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Add your docs here.
 */
public class LEDstrip {
    private AddressableLEDBuffer ledBuffer;
    private AddressableLED led;

    public LEDstrip(int port, int LedCount) {
        // PWM port 9
        // Must be a PWM header, not MXP or DIO
        led = new AddressableLED(port);

        // Reuse buffer
        // Default to a length of 60, start empty output
        // Length is expensive to set, so only set it once, then just update data
        ledBuffer = new AddressableLEDBuffer(LedCount);
        led.setLength(ledBuffer.getLength());

        // Set the data
        led.setData(ledBuffer);
        led.start();
    }

    public void setColor(Color input) {
        for (int i = 0; i < ledBuffer.getLength(); i++){
            ledBuffer.setLED(i, input);
        }

        led.setData(ledBuffer);
    }

    public void setColor(int r, int g, int b){
        for (int i = 0; i < ledBuffer.getLength(); i++)
            ledBuffer.setRGB(i,r,g,b);

        led.setData(ledBuffer);
    }

    /**
     * maps the lookup table onto the leds with an optional offset
     */
    public void mapLookupTable(ColorLookUpTable table, double offset){
        for (int i = 0; i < ledBuffer.getLength(); i++){
            ledBuffer.setLED(i, table.getColor(((double)i / ledBuffer.getLength()) + offset));
        }        
        led.setData(ledBuffer);
    }
}
