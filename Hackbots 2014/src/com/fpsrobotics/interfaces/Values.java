/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpsrobotics.interfaces;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.CriteriaCollection;

/**
 *
 * @author Ben
 */
public interface Values
{
    //Motor Speeds

    double MAX_SPEED = 1.0;
    double HALF_SPEED = 0.5;
    double NO_SPEED = 0.0;
    // For image processing
    //Put more important reusable values here
    //Camera constants used for distance calculation
    int Y_IMAGE_RES = 240;		//X Image resolution in pixels, should be 120, 240 or 480
    double VIEW_ANGLE = 49;		//Axis M1013
    //final double VIEW_ANGLE = 41.7;		//Axis 206 camera
    //final double VIEW_ANGLE = 37.4;  //Axis M1011 camera
    double PI = 3.141592653;
    //Score limits used for target identification
    int RECTANGULARITY_LIMIT = 40;
    int ASPECT_RATIO_LIMIT = 55;
    //Score limits used for hot target determination
    int TAPE_WIDTH_LIMIT = 50;
    int VERTICAL_SCORE_LIMIT = 50;
    int LR_SCORE_LIMIT = 50;
    //Minimum area of particles to be considered
    int AREA_MINIMUM = 150;
    //Maximum number of particles to process
    int MAX_PARTICLES = 8;

    // Are we in PID mode?
    boolean SimplePIDMode = false;
}
