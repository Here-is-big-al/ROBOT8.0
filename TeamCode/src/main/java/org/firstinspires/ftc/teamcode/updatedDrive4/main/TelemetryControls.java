package org.firstinspires.ftc.teamcode.updatedDrive4.main;

import static org.firstinspires.ftc.teamcode.updatedDrive3.main.CameraControls.maxExposure;
import static org.firstinspires.ftc.teamcode.updatedDrive3.main.CameraControls.maxGain;
import static org.firstinspires.ftc.teamcode.updatedDrive3.main.CameraControls.minExposure;
import static org.firstinspires.ftc.teamcode.updatedDrive3.main.CameraControls.minGain;
import static org.firstinspires.ftc.teamcode.updatedDrive3.main.CameraControls.myExposure;
import static org.firstinspires.ftc.teamcode.updatedDrive3.main.CameraControls.myGain;
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
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.PoseStorage.poseEstimate;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.currentMode;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.currentMovement;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.currentState;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TelemetryControls{

    private static Robot robot;

    private static Telemetry telemetry;

    private static Map<String, Object[] > addedTelemetry = new HashMap<>();

    public TelemetryControls(Robot rob, Telemetry telem) {
        robot = rob;
        telemetry = telem;

    }

    public static void update() {
        //distance sensor

        //modes, states, and movement
        telemetry.addData("mode", currentMode);
        telemetry.addData("state", currentState);
        telemetry.addData("movement", currentMovement);

        //pose

        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());

        telemetry.addData("Added telemetry", "");

        //addedTelemetry
        telemetry.addData("INTAKE_START_POWER", INTAKE_START_POWER);
        for (String key : addedTelemetry.keySet()) {
            telemetry.addData(key, " value - "
                    + Objects.requireNonNull(addedTelemetry.get(key))[0] +
                    ", time - " + Objects.requireNonNull(addedTelemetry.get(key))[1]);

        }

        telemetry.addData("Exposure1","%d  (%d - %d)", exposure1, minExposure1, maxExposure1);
        telemetry.addData("Gain1","%d  (%d - %d)", gain1, minGain1, maxGain1);
        telemetry.addData("Exposure2","%d  (%d - %d)", exposure2, minExposure2, maxExposure2);
        telemetry.addData("Gain2","%d  (%d - %d)", gain2, minGain2, maxGain2);

        //the rest of the objects detected
        telemetry.addData("", "---------------"); //15 dashes

        updateTfodTelemAndDetectionIndex();

        //additional info
        telemetry.addData("", "---------------"); //15 dashes

        telemetry.update();

    }

    //updates the telemetry for the tfod
    public static void updateTfodTelemAndDetectionIndex() {

    }

    //add custom messages
    public static void add(String name, String message, Double time) {
        addedTelemetry.put(name, new Object[]{message, time});

    }

    public static void add(String name, String message) {
        telemetry.addData(name, message);
        telemetry.update();

    }

    public static void add(String name, double number) {
        telemetry.addData(name, number);
        telemetry.update();

    }

    public static void add(String message) {
        telemetry.addLine(message);
        telemetry.update();

    }
}
