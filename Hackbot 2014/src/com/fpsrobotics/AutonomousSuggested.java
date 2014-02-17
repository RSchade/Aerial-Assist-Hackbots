//package com.fpsrobotics;
//
///**
// *
// * @author Josh
// */
//public class AutonomousSuggested
//{
//
//    private boolean autoDone = false;
//    CatapultSuggestedA autoLaunch = new CatapultSuggestedA();
//    Thread autoLaunchThread = new Thread(autoLaunch);
//
//    public void autonomous()
//    {
//
//
//
//        try
//        {
//            if (visionSample.autoImageFind() && autoDone)
//            {
//                goodImageCounter++;
//            }
//            if (goodImageCounter > 2)
//            {
//                goodImageCounter = 0;
//                autoDone = true;
//
//                // **SHOOT TO AUTONOMOUS PRESETS**
//                autoLaunchThread.start();
//                autoLaunch.autonomous();
//
//                    //**AUTO DRIVE FUNCTION**
//                    DriveTrainThreadSuggestedB autoDrive = new DriveTrainThreadSuggestedB();
//                    autoDrive.set(0.7, 0.7);
//            }
//
//            hackbotWatch.feed(dog);
//
//        } catch (AxisCameraException ex)
//        {
//            ex.printStackTrace();
//        }
//    }
//}
