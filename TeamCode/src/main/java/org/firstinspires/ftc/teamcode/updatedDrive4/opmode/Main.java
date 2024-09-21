package org.firstinspires.ftc.teamcode.updatedDrive4.opmode;

import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.RESTING_INTAKE_POWER;
import static org.firstinspires.ftc.teamcode.updatedDrive4.constants.Constants.TRACK_WIDTH;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.PoseStorage.poseEstimate;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.Mode;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.Movement;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.StartPoseEnumerated;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.State;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.currentMode;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.currentMovement;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.currentState;
import static org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions.startPoseEnumerated;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.updatedDrive4.main.Robot;
import org.firstinspires.ftc.teamcode.updatedDrive4.main.TelemetryControls;
import org.firstinspires.ftc.teamcode.updatedDrive4.objects.Drivetrain;
import org.firstinspires.ftc.teamcode.updatedDrive4.storage.PoseStorage;
import org.firstinspires.ftc.teamcode.updatedDrive4.storage.Positions;


/**
 * The main opmode for the robot
 * <p>
 *
 * //GAMEPAD CONTROLS
 * <p>
 * Universal
 * x    switch mode to driver control, intake off, cancel following
 * y    switch mode to auto control, intake off
 * a    switch state to locate
 * b    switch state to idle
 * <p>
 * Driver control
 * left bumper      intake off
 * right bumper     intake on
 * left trigger     intake reverse with variable speed
 * right trigger    intake collect with variable speed
 */


//TODO: Add distance sensor to prevent driving into solid objects and add virtual map for path planning


@TeleOp(name="updatedDrive4Main", group = "APushBot")
//@Disabled
public class Main extends LinearOpMode {
    public static int locateRotations = 0;
    public static int collectRotations = 0;
    public static int collectStraights = 0;

    private boolean firstAuto = true; //have we been in auto yet or have we just picked up a ball?
    private boolean firstDetection = true; //is this the first time detecting objects?
    ElapsedTime timer = new ElapsedTime();
    ElapsedTime timer1 = new ElapsedTime();

    private int ballsCollected = 0;

    public Pose2d startPose;

    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("Wait...............");
        telemetry.update();

        robot = new Robot(hardwareMap, telemetry, gamepad1, gamepad2);

        currentMode = Mode.DRIVER_CONTROL;
        currentState = State.IDLE;
        currentMovement = Movement.DRIVER_IN_CONTROL;

        startPoseEnumerated = StartPoseEnumerated.COURT_FOUR;
        startPose = Positions.getStartPose();

        robot.drivetrain.setPoseEstimate(startPose);
        PoseStorage.currentPose = robot.drivetrain.getPoseEstimate();


        //set previous trajectory as the starting point
        robot.drivetrain.currentTrajectory = robot.drivetrain.trajectoryBuilder(startPose).forward(0.000001).build();

        telemetry.addLine("Ready");
        telemetry.update();

        //robot.cameraControls.visionPortal1.stopLiveView();
        //robot.cameraControls.visionPortal2.stopLiveView();
        //robot.cameraControls.visionPortal2.stopStreaming();

        while (!opModeIsActive() && !isStopRequested()) {
            robot.gamepadControls.universalControls();
            TelemetryControls.update();

        }

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {
            poseEstimate = robot.drivetrain.getPoseEstimate();

            //checks and does actions based on the universal gamepad controls
            //which occur no matter what
            robot.gamepadControls.universalControls();

            //updates the drivetrain, distance sensor, tfod, AprilTags, etc
            //Includes telemetry update
            robot.update();

            switch (currentMode) {
                case AUTO_CONTROL:

                    if (currentState == State.IDLE) {
                        //stop movement and intake
                        robot.drivetrain.breakFollowingSmooth();

                        currentMovement = Movement.IDLE;

                    } else {
                        if (currentState != State.DROPPING_OFF && ballsCollected > 9) {
                            //if the robot has collected enough targets, head home if not idle or dropping off
                            currentState = State.HEADING_HOME;

                        }

                    }

                    break;

                case DRIVER_CONTROL:
                    //gamepad controls that only occur if the mode is driver control
                    robot.gamepadControls.driverControlControls();

                    currentMovement = Movement.DRIVER_IN_CONTROL;

                    break;

                case FIRST_AUTO_CONTROL:

                    if (firstAuto) {
                        timer.reset();
                        firstAuto = false;

                    } else if (timer.seconds() > 2.0) {
                        firstAuto = true;
                        currentMode = Mode.AUTO_CONTROL;
                        currentState = State.LOCATING;

                    }

            }

            //sleep(300);

            //updates only the telemetry
            TelemetryControls.update();

            sleep(50);

        }

    }


    /** FUNCTIONS ***/


    //TODO: finish these functions
    //find new location using virtual map
    private void findNewLocation() {

    }

    //drives to new location avoiding objects
    private void driveToNewLocation() {

    }

}
