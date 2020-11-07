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
    private String city;
    private TextView textViewPressureValue;
    private TextView textViewWindSpeedValue;
    private TextView textViewMoistureValue;
    private static final String TAG = WeatherDescription.class.getSimpleName();
    private static final String INSTANCE_TEMPERATURE="INSTANCE_TEMPERATURE";
    private static final String INSTANCE_PRESSURE = "INSTANCE_PRESSURE";
    private static final String INSTANCE_SPEED = "INSTANCE_SPEED";
    private static final String INSTANCE_MOISTURE = "INSTANCE_MOISTURE";
    private TextView textViewCity;
    private TextView textViewTemperature;
    private ImageButton imageButtonFavourites;
    private boolean flag = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_description);

        textViewCity = findViewById(R.id.textViewCity);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        imageButtonFavourites = findViewById(R.id.imageButtonFavourites);
        textViewPressureValue= findViewById(R.id.textViewPressureValue);
        textViewWindSpeedValue=findViewById(R.id.textViewWindSpeedValue);
        textViewMoistureValue=findViewById(R.id.textViewMoistureValue);

        imageButtonFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    imageButtonFavourites.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.btn_star_big_on));
                    flag = true;
                   // Intent intent=new Intent(WeatherDescription.this, MainActivity.class);
                   // intent.putExtra(Keys.KEY, city);
                    //Log.d(TAG, "передано" + city);
                } else {
                    imageButtonFavourites.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.btn_star_big_off));
                    flag = false;
                }
            }
        });

        // Intent intent = getIntent();
        // String value = intent.getStringExtra(Keys.KEY);
        city = getIntent().getStringExtra(Keys.KEY);
        boolean pressure = getIntent().getBooleanExtra("pressure", false);
        boolean speed=getIntent().getBooleanExtra("speed", false);
        boolean moisture=getIntent().getBooleanExtra("moisture", false);


        if (city != null) {
            Log.d(TAG, "получено значение " + city);
            textViewCity.setText(city);
            textViewTemperature.setText(showRandomValue() + " °");
            if (pressure){
                textViewPressureValue.setText(showRandomValue()  + " мм рт.ст");
            }
            if (speed){
                textViewWindSpeedValue.setText(showRandomValue() + " м/c");
            }
            if (moisture){
                textViewMoistureValue.setText(showRandomValue()+ " %");
            }
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
        saveInstanceState.putString(INSTANCE_SPEED, textViewWindSpeedValue.getText().toString());
        saveInstanceState.putString(INSTANCE_PRESSURE, textViewPressureValue.getText().toString());
        saveInstanceState.putString(INSTANCE_MOISTURE, textViewMoistureValue.getText().toString());

    }
    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        String temp = saveInstanceState.getString(INSTANCE_TEMPERATURE);
        textViewTemperature.setText(temp);
        textViewMoistureValue.setText(saveInstanceState.getString(INSTANCE_MOISTURE));
        textViewWindSpeedValue.setText(saveInstanceState.getString(INSTANCE_SPEED));
        textViewPressureValue.setText(saveInstanceState.getString(INSTANCE_PRESSURE));
    }

    private static String showRandomValue() {// временны вариант рандомных показателей
        int random = (int) (Math.random() * 30);
        return String.valueOf(random);
    }

    @Override
    public void onBackPressed() {
        if(flag){
            Intent intentResult = new Intent(WeatherDescription.this, MainActivity.class);
            intentResult.putExtra("favourite_city", city);
            setResult(RESULT_OK, intentResult);
            Log.d(TAG, "передано " + city);
            finish();
        }
        super.onBackPressed();
    }
}


// SingleTone -это класс, объект которого существует в единственном экземпляре
// интент - своего рода намерение выполнить какую-то операцию , позволяет связать разнородные объекты, например две активности
//интент это объект, при помощи которого осуществляется взаимодействие между компонентами системы
// создаем явный интент (первый параметр - от какой активности поступает интент, второй - какая активность получит интент) и вызываем метод startActivity (intent)
// в интент можно вложить инфу, которую надо передать
// создаем неявный интент Intent intent = new Intent (действие)
// Intent.ACTION_DIAL - НАБОР НОМЕРА Intent.ACTION_WEB_SEARCH - ВЕБ ПОИСК Intent.ACTION_SEND - ОТПРАВКА СООБЩЕНИЙ
// В интент можно добавить информацию. 1 - установить тип передаваемых данных - intent.setType ("text/plain")
// 2 - intent.putExtra(Intent.EXTRA_TEXT, текст) intent.EXTRA_SUBJECT, тема
// чтение строки из ресурсов getString(R.string.имя строки)