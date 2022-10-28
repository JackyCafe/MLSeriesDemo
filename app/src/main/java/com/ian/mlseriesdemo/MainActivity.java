package com.ian.mlseriesdemo;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseLandmark;
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    private ImageView inputImageView;
    private View outputTextView;
    public final static int REQUEST_READ_EXTERNAL_STORAGE = 2031;
    public final static int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 1064;
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    AccuratePoseDetectorOptions options;
    Bitmap bm;
    PoseDetector poseDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputImageView = findViewById(R.id.imageView);
        outputTextView = findViewById(R.id.textView);
        options =  new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.SINGLE_IMAGE_MODE)
                .build();
        //建立 PoseDetector 的執行個體。傳送您指定的選項：
        poseDetector = PoseDetection.getClient(options);
        bm = loadFromAsset();
        inputImageView.setImageBitmap(bm);

    }

    private Bitmap loadFromAsset(){
        Bitmap bm =  null;
        try {
            InputStream in = getAssets().open("tiger.jpg");
            bm = BitmapFactory.decodeStream(in).copy(Bitmap.Config.ARGB_8888,true);
        }catch (Exception e){

        }
        return bm;
    }

    public void onPickImage(View view) {
        InputImage image = InputImage.fromBitmap(bm, 0);


        Task<Pose> result =
                poseDetector.process(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<Pose>() {
                                    @Override
                                    public void onSuccess(Pose pose) {
                                        List<PoseLandmark> allPoseLandmarks = pose.getAllPoseLandmarks();

// Or get specific PoseLandmarks individually. These will all be null if no person
// was detected
                                        PoseLandmark leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER);
                                        PoseLandmark rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER);
                                        PoseLandmark leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW);
                                        PoseLandmark rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW);
                                        PoseLandmark leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST);
                                        PoseLandmark rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST);
                                        PoseLandmark leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP);
                                        PoseLandmark rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP);
                                        PoseLandmark leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE);
                                        PoseLandmark rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE);
                                        PoseLandmark leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE);
                                        PoseLandmark rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE);
                                        PoseLandmark leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY);
                                        PoseLandmark rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY);
                                        PoseLandmark leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX);
                                        PoseLandmark rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX);
                                        PoseLandmark leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB);
                                        PoseLandmark rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB);
                                        PoseLandmark leftHeel = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL);
                                        PoseLandmark rightHeel = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL);
                                        PoseLandmark leftFootIndex = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX);
                                        PoseLandmark rightFootIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX);
                                        PoseLandmark nose = pose.getPoseLandmark(PoseLandmark.NOSE);
                                        PoseLandmark leftEyeInner = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_INNER);
                                        PoseLandmark leftEye = pose.getPoseLandmark(PoseLandmark.LEFT_EYE);
                                        PoseLandmark leftEyeOuter = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_OUTER);
                                        PoseLandmark rightEyeInner = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_INNER);
                                        PoseLandmark rightEye = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE);
                                        PoseLandmark rightEyeOuter = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_OUTER);
                                        PoseLandmark leftEar = pose.getPoseLandmark(PoseLandmark.LEFT_EAR);
                                        PoseLandmark rightEar = pose.getPoseLandmark(PoseLandmark.RIGHT_EAR);
                                        PoseLandmark leftMouth = pose.getPoseLandmark(PoseLandmark.LEFT_MOUTH);
                                        PoseLandmark rightMouth = pose.getPoseLandmark(PoseLandmark.RIGHT_MOUTH);
                                        PointF leftShoulderP =  leftShoulder.getPosition();
                                        float leftShoulderX = leftShoulderP.x;
                                        float leftShoulderY = leftShoulderP.y;
                                        PointF leftElbowP =  leftElbow.getPosition();
                                        float leftElbowX = leftElbowP.x;
                                        float leftElbowY = leftElbowP.y;
                                        PointF leftWristP =  leftWrist.getPosition();
                                        float leftWristX = leftWristP.x;
                                        float leftWristY = leftWristP.y;

                                        PointF rightShoulderP =  rightShoulder.getPosition();
                                        float rightShoulderX = rightShoulderP.x;
                                        float rightShoulderY = rightShoulderP.y;
                                        PointF rightElbowP =  rightElbow.getPosition();
                                        float rightElbowX = rightElbowP.x;
                                        float rightElbowY = rightElbowP.y;
                                        PointF rightWristP =  rightWrist.getPosition();
                                        float rightWristX = rightWristP.x;
                                        float rightWristY = rightWristP.y;
                                        displayAll(leftShoulderX,leftShoulderY,leftElbowX
                                                ,leftElbowY,leftWristX,leftWristY
                                                ,rightShoulderX,rightShoulderY
                                                ,rightElbowX,rightElbowY
                                                ,rightWristX,rightWristY
                                        );

                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });


    }

    public void displayAll(float leftShoulderX,float leftShoulderY,float leftElbowX
            ,float leftElbowY,float leftWristX,float leftWristY
            ,float rightShoulderX, float rightShoulderY
            ,float rightElbowX,float rightElbowY
            ,float rightWristX,float rightWristY)
    {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        float strokeWidth = 20.0f;
        paint.setStrokeWidth(strokeWidth);
        Bitmap drawBitmap = Bitmap.createBitmap(
                bm.getWidth(),
                bm.getHeight(),
                bm.getConfig()
        );
        Bitmap newBitmap = Bitmap.createBitmap(bm);

        Canvas canvas = new Canvas(drawBitmap);
        canvas.drawBitmap(newBitmap, 0f, 0f, null);
        canvas.drawLine(leftShoulderX, leftShoulderY, rightShoulderX, rightShoulderY, paint);
        canvas.drawLine(leftShoulderX, leftShoulderY, leftElbowX, leftElbowY, paint);
        canvas.drawLine(leftElbowX, leftElbowY, leftWristX, leftWristY, paint);
        canvas.drawLine(rightShoulderX, rightShoulderY, rightElbowX, rightElbowY, paint);
        canvas.drawLine(rightElbowX, rightElbowY, rightWristX, rightWristY, paint);
        inputImageView.setImageBitmap(drawBitmap);
        Log.v("Ian","leftShoulderX:" +leftShoulderX+",leftShoulderY"+leftShoulderY);
    }
}

