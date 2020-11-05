package com.example.weatherforecastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WeatherDescription extends AppCompatActivity {
    private static final String TAG = WeatherDescription.class.getSimpleName();
    TextView textViewCity;
    TextView textViewDay_temp_degrees;
    TextView textViewNight_temp_degrees;
    TextView textViewPressure;
    TextView textViewWindSpeed;
    TextView textViewMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_description);

        textViewCity = findViewById(R.id.textViewCity);
        textViewDay_temp_degrees=findViewById(R.id.textViewDay_temp_degrees);
        textViewNight_temp_degrees=findViewById(R.id.textViewNight_temp_degrees);
        textViewPressure=findViewById(R.id.textViewPressure);
        textViewWindSpeed=findViewById(R.id.textViewWindSpeed);
        textViewMeasure = findViewById(R.id.textViewMeasure);


        String value = getIntent().getStringExtra(Keys.KEY);

        if (value!=null){
            Log.d(TAG, "получено значение "+ value);
            textViewCity.setText(value);
            textViewDay_temp_degrees.setText(showRandomValue());
            textViewNight_temp_degrees.setText(showRandomValue());
        } else {
            Log.d(TAG, "value is null");
        }

    }

    private static String showRandomValue(){// временны вариант рандомных показателей
        int random = (int) (Math.random() * 30);
        return String.valueOf(random);
    }


}
// SingleTone -это класс, объект которого существует в единственном экземпляре