package com.vinkisoft.adcapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.camerakit.CameraKitView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import java.util.List;

public class LivePreviewActivity extends AppCompatActivity {

    private CameraKitView cameraKitView;
    private GraphicOverlay graphicOverlay;
    Button buttonDetect;
    Bundle bundle;
    String selfieType = "";
    TextView textViewMessage;
    private int facesCount;
    private float smileValue;
    private boolean isEyeOpen = false;


    private static final String SELFIE_TYPE = "SelfieType";
    private static final String SELFIE_SINGLE = "Single";
    private static final String SELFIE_SMILE = "Smile";
    private static final String SELFIE_PAIR = "Pair";
    private static final String SELFIE_FAMILY = "Family";
    private static final String SELFIE_FRIENDS = "Friends";
    private static final String SELFIE_WINK = "Wink";
    private static final String SELFIE_GROUP = "Group";


    FirebaseVisionFaceDetector firebaseVisionFaceDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_live_preview);
        cameraKitView = findViewById(R.id.cameraKitView);
        graphicOverlay = findViewById(R.id.graphicOverlay);
        buttonDetect = findViewById(R.id.buttonDetect);
        buttonDetect.setVisibility(View.GONE);
        textViewMessage = findViewById(R.id.textViewMessage);

        bundle = getIntent().getExtras();
        selfieType = bundle.getString(SELFIE_TYPE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here.
                textViewMessage.setVisibility(View.GONE);
                buttonDetect.performClick();

            }
        }, 3000);


        buttonDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Ok Ready." + selfieType, Toast.LENGTH_SHORT).show();
                startCamera();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraKitView.stopVideo();
        cameraKitView.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cameraKitView.stopVideo();
        cameraKitView.onStop();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cameraKitView.stopVideo();
        cameraKitView.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.startVideo();
        cameraKitView.onResume();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void startCamera() {

        cameraKitView.startVideo();
        cameraKitView.captureImage(new CameraKitView.ImageCallback() {
            @Override
            public void onImage(CameraKitView cameraKitView, byte[] bytes) {

                cameraKitView.stopVideo();
                processFaceDetection(bytes);
                graphicOverlay.clear();

            }
        });
    }

    private void processFaceDetection(byte[] byteArray) {

        FirebaseVisionImageMetadata metadata = new FirebaseVisionImageMetadata.Builder()
                .setWidth(480)   // 480x360 is typically sufficient for
                .setHeight(360)  // image recognition
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                // .setRotation(rotation)
                .build();

        FirebaseVisionImage image = FirebaseVisionImage.fromByteArray(byteArray, metadata);

        // High-accuracy landmark detection and face classification
        FirebaseVisionFaceDetectorOptions highAccuracyOpts =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                        .setLandmarkMode(FirebaseVisionFaceDetectorOptions.NO_LANDMARKS)
                        .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .build();

        firebaseVisionFaceDetector =
                FirebaseVision.getInstance().getVisionFaceDetector(highAccuracyOpts);

        firebaseVisionFaceDetector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
            @Override
            public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
                getFaceResults(firebaseVisionFaces);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    private void getFaceResults(List<FirebaseVisionFace> firebaseVisionFaces) {
        int counter = 0;

        for (FirebaseVisionFace face : firebaseVisionFaces) {
            Rect rect = face.getBoundingBox();
            RectOverlay rectOverlay = new RectOverlay(graphicOverlay, rect);
            graphicOverlay.add(rectOverlay);
            counter = counter + 1;
        }
        Toast.makeText(getApplicationContext(), "Faces : " + counter, Toast.LENGTH_LONG).show();
        captureSelfie();

    }

    private void captureSelfie(){

        if (selfieType != "" && selfieType != null) {

            switch (selfieType){
                case SELFIE_SINGLE:
                    // If the number of detected face = 1
                    //TODO: Capture a Single Selfie
                    break;
                case SELFIE_SMILE:
                    // If the smile of detected face(s) probability > 0.30
                    //TODO: Capture a Smile Selfie
                    break;
                case SELFIE_PAIR:
                    // If the number of detected faces = 2
                    //TODO: Capture a Pair Selfie

                    break;
                case SELFIE_FRIENDS:
                    // If the number of detected faces = 3 or 4 0r 5
                    //TODO: Capture a Friends Selfie

                    break;
                case SELFIE_FAMILY:

                    // If the number of detected faces = 3 or 4
                    //TODO: Capture a Family Selfie
                    break;
                case SELFIE_WINK:
                    // If the number of detected faces with Left or Right closed
                    //TODO: Capture a Wink Selfie

                    break;
                case SELFIE_GROUP:
                    // If the number of detected faces > 5
                    //TODO: Capture a Groupie

                    break;
                    default:
                        break;

            }
        }
    }


}
