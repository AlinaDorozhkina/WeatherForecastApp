package com.example.weatherforecastapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
    private TextView textViewCity;
    private TextView textViewTemperature;
    private TextView textViewPressure;
    private TextView textViewWindSpeed;
    private TextView textViewMeasure;
    private ImageButton imageButtonFavourites;
    private boolean flag = true;

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
    protected void onStart() {
        // вызывается после onCreate, активити еще не видима,  после  активити станет видимой
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        // опциональный метод, вызывается после onRestart, чтобы восстановить визуальные элементы активити, на вход принимает бандл - инфо , которую хотим сохранить
        // если бандл пуст, метод не вызовется
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Повторный запуск!! - onRestoreInstanceState()");
    }


    @Override
    protected void onResume() { // Foreground LifeTime - пользователь видит и взаимодействует с активити
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() { //можно освобождать ресурсы,  пользователь видит экран (Foreground LifeTime), в случае мульти-дисплея (две активити н разных дисплеях) не освобождать здесь ресурсы
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause()");

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) { // не обязательно, что вызовется
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onSaveInstanceState()");
    }

    @Override
    protected void onStop() { // освобождаем нужные ресурсы, пользователь не видит экран, если возвращается к этой активити - вызовется метод onRestart, потом onStart
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy()");
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