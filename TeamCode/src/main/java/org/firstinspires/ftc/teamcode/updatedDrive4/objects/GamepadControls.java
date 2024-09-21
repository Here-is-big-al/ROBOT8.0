package org.firstinspires.ftc.teamcode.updatedDrive4.objects;

import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.INTAKE_START_POWER;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.exposure1;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.exposure2;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.gain1;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.gain2;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.maxExposure1;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.maxExposure2;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.maxGain1;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.maxGain2;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.minExposure1;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.minExposure2;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.minGain1;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.minGain2;
import static org.firstinspires.ftc.teamcode.updatedDrive4.opmode.Main.collectRotations;
import static org.firstinspires.ftc.teamcode.updatedDrive4.opmode.Main.collectStraights;
import static org.firstinspires.ftc.teamcode.updatedDrive4.opmode.Main.locateRotations;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.Mode;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.Movement;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.State;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.currentMode;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.currentMovement;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.currentState;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.updatedDrive4.main.Robot;


/**
 * The gamepad
 *
 *
 * //GAMEPAD1 CONTROLS
 *
 * Universal
 * x    switch mode to driver control, intake off, stop robot
 * y    switch mode to auto control, intake off
 * a    switch state to locate
 * b    switch state to idle
 *
 * Driver control
 * left bumper      intake off
 * right bumper     intake on
 * left trigger     intake reverse with variable speed
 * right trigger    intake collect with variable speed
 *
 * GAMEPAD2 CONTROLS
 *
 * Universal
 *
 *
 */


public class GamepadControls {

    private Robot robot;

    private Gamepad gamepad1, gamepad2;

    private boolean lastLeftTrigger2 = false, lastRightTrigger2 = false, lastLeftBumper2 = false, lastRightBumper2 = false;

    public GamepadControls(Robot rob, Gamepad gamepadUno, Gamepad gamepadDos) {
        robot = rob;
        gamepad1 = gamepadUno;
        gamepad2 = gamepadDos;
    }

    //allows the gamepad to change mode and state
    public void universalControls() throws InterruptedException {



    }

    //allows the gamepad to do certain controls during the driver period
    public void driverControlControls() {
        //used for driver control-specific commands
        robot.drivetrain.setWeightedDrivePower(
                new Pose2d(
                        (gamepad1.dpad_up ? 1 : (gamepad1.dpad_down ? -1 : -gamepad1.left_stick_y)),
                        (gamepad1.dpad_left ? 1 : (gamepad1.dpad_right ? -1 : -gamepad1.left_stick_x)),
                        -gamepad1.right_stick_x
                )
        );


    }
}
