package com.example.weatherforecastapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherDescription extends AppCompatActivity {
    private String value;
    private static final String TAG = WeatherDescription.class.getSimpleName();
    private static final String INSTANCE_TEMPERATURE="INSTANCE_TEMPERATURE";
    private TextView textViewCity;
    private TextView textViewTemperature;
    private TextView textViewPressure;
    private TextView textViewWindSpeed;
    private TextView textViewMeasure;
    private ImageButton imageButtonFavourites;
    private boolean flag = true;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_description);

        textViewCity = findViewById(R.id.textViewCity);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewPressure = findViewById(R.id.textViewPressure);
        textViewWindSpeed = findViewById(R.id.textViewWindSpeed);
        textViewMeasure = findViewById(R.id.textViewMeasure);
        imageButtonFavourites = findViewById(R.id.imageButtonFavourites);

        imageButtonFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    imageButtonFavourites.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.btn_star_big_on));
                    flag = false;
                    Intent intent=new Intent(WeatherDescription.this, MainActivity.class);
                    intent.putExtra(Keys.KEY, value);
                    Log.d(TAG, "передано" + value);
                } else {
                    imageButtonFavourites.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.btn_star_big_off));
                    flag = true;
                }
            }
        });

        // Intent intent = getIntent();
        // String value = intent.getStringExtra(Keys.KEY);
        value = getIntent().getStringExtra(Keys.KEY);

        if (value != null) {
            Log.d(TAG, "получено значение " + value);
            textViewCity.setText(value);
            textViewTemperature.setText(showRandomValue() + " °");
            //textViewNight_temp_degrees.setText(showRandomValue());
        } else {
            Log.d(TAG, "value is null");
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onSaveInstanceState()");
        saveInstanceState.putString(INSTANCE_TEMPERATURE, textViewTemperature.getText().toString());

    }
    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        String temp = saveInstanceState.getString(INSTANCE_TEMPERATURE);
        textViewTemperature.setText(temp);
    }

    private static String showRandomValue() {// временны вариант рандомных показателей
        int random = (int) (Math.random() * 30);
        return String.valueOf(random);
    }


}


// SingleTone -это класс, объект которого существует в единственном экземпляре
// интент - своего рода намерение выполнить какую-то операцию , позволяет связать разнородные объекты, например две активности
// создаем явный интент (первый параметр - от какой активности поступает интент, второй - какая активность получит интент) и вызываем метод startActivity (intent)
// в интент можно вложить инфу, которую надо передать
// создаем неявный интент Intent intent = new Intent (действие)
// Intent.ACTION_DIAL - НАБОР НОМЕРА Intent.ACTION_WEB_SEARCH - ВЕБ ПОИСК Intent.ACTION_SEND - ОТПРАВКА СООБЩЕНИЙ
// В интент можно добавить информацию. 1 - установить тип передаваемых данных - intent.setType ("text/plain")
// 2 - intent.putExtra(Intent.EXTRA_TEXT, текст) intent.EXTRA_SUBJECT, тема
// чтение строки из ресурсов getString(R.string.имя строки)