package com.example.weatherforecastapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherDescription extends AppCompatActivity {
    private ExtraDataFragment extraDataFragment;
    private String city;
    private TextView textViewPressureValue;
    private TextView textViewWindSpeedValue;
    private TextView textViewMoistureValue;
    private TextView textWebSite;
    private static final String TAG = WeatherDescription.class.getSimpleName();
    private static final String INSTANCE_TEMPERATURE = "INSTANCE_TEMPERATURE";
    private static final String INSTANCE_PRESSURE = "INSTANCE_PRESSURE";
    private static final String INSTANCE_SPEED = "INSTANCE_SPEED";
    private static final String INSTANCE_MOISTURE = "INSTANCE_MOISTURE";
    private TextView textViewCity;
    private TextView textViewTemperature;
    private ImageButton imageButtonFavourites;
    private boolean flag = false;

    private FragmentManager manager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_description);

        textViewCity = findViewById(R.id.textViewCity);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        imageButtonFavourites = findViewById(R.id.imageButtonFavourites);
        // textViewPressureValue = findViewById(R.id.textViewPressureValue);
        // textViewWindSpeedValue = findViewById(R.id.textViewWindSpeedValue);
        //  textViewMoistureValue = findViewById(R.id.textViewMoistureValue);
        textWebSite = findViewById(R.id.textWebSite);
        Button buttonWebSearch = findViewById(R.id.buttonWebSearch);

        imageButtonFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    imageButtonFavourites.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.btn_star_big_on));
                    flag = true;
                } else {
                    imageButtonFavourites.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.btn_star_big_off));
                    flag = false;
                }
            }
        });

        city = getIntent().getStringExtra(Keys.KEY);

        if (city != null) {
            Log.d(TAG, "получено значение " + city);
            textViewCity.setText(city);
            textViewTemperature.setText(String.format("%s °", showRandomValue()));
        }
        boolean pressure = getIntent().getBooleanExtra("pressure", false);
        boolean speed = getIntent().getBooleanExtra("speed", false);
        boolean moisture = getIntent().getBooleanExtra("moisture", false);

        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();
        if (pressure || speed || moisture) {

            extraDataFragment = new ExtraDataFragment();
            transaction.add(R.id.frame_for_extra_layout, extraDataFragment);
            Bundle bundle = new Bundle();
            bundle.putBoolean("pressure", pressure);
            bundle.putBoolean("speed", speed);
            bundle.putBoolean("moisture", moisture);
            extraDataFragment.setArguments(bundle);
            transaction.commit();

        } else {
            PictureFragment pictureFragment=new PictureFragment();
            transaction.add(R.id.frame_for_extra_layout, pictureFragment);
            transaction.commit();

        }


        buttonWebSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uriStr = textWebSite.getText().toString();
                Uri uri = Uri.parse(uriStr);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
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
        if (flag) {
            Intent intentResult = new Intent(WeatherDescription.this, MainActivity.class);
            intentResult.putExtra(Keys.CITY, city);
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