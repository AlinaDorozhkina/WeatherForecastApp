package com.example.weatherforecastapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView autoCompleteTextView;
    private static  final int REQUEST_ACCESS_TYPE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // вызывется при создании или перезапуске активити, передается бандл, если передаем параметр какой-то
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_show = findViewById(R.id.button_show);

        autoCompleteTextView=findViewById(R.id.autoCompleteTextView);

        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, WeatherDescription.class);
                String value = autoCompleteTextView.getText().toString();
                Log.d(TAG, "передача бандла "+ value);
                intent.putExtra(Keys.KEY, value);
                startActivity(intent);
            }
        });

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "Первый запуск!";
            Log.d(TAG, "лог: первый запуск");
        } else {
            instanceState = "Повторный запуск!";
            Log.d(TAG, "лог: повторный запуск");
        }
        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "on create");


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


}