package org.firstinspires.ftc.teamcode.updatedDrive4.main;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.updatedDrive4.objects.Drivetrain;
import org.firstinspires.ftc.teamcode.updatedDrive4.objects.GamepadControls;
import org.firstinspires.ftc.teamcode.updatedDrive4.objects.Imu;


/**
 * This sets up the robot and
 * is how all other functions of the robot
 * are accessed
 */


public class Robot {
    //physical objects
    public Drivetrain drivetrain;


    public Imu imu;

    public GamepadControls gamepadControls;

    //Virtual functions and processorsf
    public TelemetryControls telemetryControls;


    //public AprilTag aprilTag;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepadUno, Gamepad gamepadDos) throws InterruptedException {
        drivetrain = new Drivetrain(hardwareMap, this);
        imu = new Imu(hardwareMap);
        gamepadControls = new GamepadControls(this, gamepadUno, gamepadDos);

        telemetryControls = new TelemetryControls(this, telemetry);


        //aprilTag = new AprilTag(hardwareMap);

    }

    public void update() {
        //update roadrunner
        drivetrain.update();


        //update the motors and their powers if need be

        //update telemetry and tfod
        TelemetryControls.update();

    }

}
