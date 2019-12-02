package com.vinkisoft.adcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button buttonSingleSelfie;
    Button buttonSmileSelfie;
    Button buttonPairSelfie;
    Button buttonFriendsSelfie;
    Button buttonWinkSelfie;
    Button buttonFamilysSelfie;
    Button buttonGroupSelfie;

    private static final String SELFIE_TYPE = "SelfieType";
    private static final String SELFIE_SINGLE = "Single";
    private static final String SELFIE_SMILE = "Smile";
    private static final String SELFIE_PAIR = "Pair";
    private static final String SELFIE_FAMILY = "Family";
    private static final String SELFIE_FRIENDS = "Friends";
    private static final String SELFIE_WINK = "Wink";
    private static final String SELFIE_GROUP = "Group";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonSingleSelfie = findViewById(R.id.buttonSingleSelfie);
        buttonSmileSelfie = findViewById(R.id.buttonSmileSelfie);
        buttonPairSelfie = findViewById(R.id.buttonPairSelfie);
        buttonFriendsSelfie = findViewById(R.id.buttonFriendsSelfie);
        buttonWinkSelfie = findViewById(R.id.buttonWinkSelfie);
        buttonFamilysSelfie = findViewById(R.id.buttonFamilysSelfie);
        buttonGroupSelfie = findViewById(R.id.buttonGroupSelfie);

        buttonSingleSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,LivePreviewActivity.class);
                intent.putExtra(SELFIE_TYPE, SELFIE_SINGLE);
                startActivity(intent);

            }
        });

        buttonSmileSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,LivePreviewActivity.class);
                intent.putExtra(SELFIE_TYPE, SELFIE_SMILE);
                startActivity(intent);

            }
        });

        buttonPairSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,LivePreviewActivity.class);
                intent.putExtra(SELFIE_TYPE, SELFIE_PAIR);
                startActivity(intent);

            }
        });

        buttonFriendsSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,LivePreviewActivity.class);
                intent.putExtra(SELFIE_TYPE, SELFIE_FRIENDS);
                startActivity(intent);

            }
        });

        buttonWinkSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,LivePreviewActivity.class);
                intent.putExtra(SELFIE_TYPE, SELFIE_WINK);
                startActivity(intent);

            }
        });

        buttonFamilysSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,LivePreviewActivity.class);
                intent.putExtra(SELFIE_TYPE, SELFIE_FAMILY);
                startActivity(intent);

            }
        });

        buttonGroupSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,LivePreviewActivity.class);
                intent.putExtra(SELFIE_TYPE, SELFIE_GROUP);
                startActivity(intent);

            }
        });

    }
}
