package com.softcare.pockettrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class HistorialCalendarioActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private Set<String> todosEjerciciosCompletados;
    private ArrayList<Object> listaFinalEjerciciosCompletados = new ArrayList<>();
    private Button btn_historial;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_calendario);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
        Button btnLeft = (Button)findViewById(R.id.buttonLeft);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = selectedDate.minusMonths(1);
                setMonthView();
            }
        });

        Button btnRight = (Button)findViewById(R.id.buttonRight);

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = selectedDate.plusMonths(1);
                setMonthView();
            }
        });

        buscarFechasEjerciciosCompletados();
        btn_historial = findViewById(R.id.btn_historial);
        btn_historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistorialCalendarioActivity.this, HistorialUsuarioActivity.class));
            }
        });
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this, listaFinalEjerciciosCompletados);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        @SuppressLint({"NewApi", "LocalSuppress"}) YearMonth yearMonth = YearMonth.from(date);
        @SuppressLint({"NewApi", "LocalSuppress"}) int daysInMonth = yearMonth.lengthOfMonth();
        @SuppressLint({"NewApi", "LocalSuppress"}) LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        @SuppressLint({"NewApi", "LocalSuppress"}) int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();


        for(int i = 1; i <= 42; i++){
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
            }else{
                //daysInMonthArray.add(String.valueOf(i - dayOfWeek));
                @SuppressLint({"NewApi", "LocalSuppress"}) LocalDate fecha = selectedDate.withDayOfMonth(i - dayOfWeek);
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                @SuppressLint({"NewApi", "LocalSuppress"}) String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                daysInMonthArray.add(fechaFormateada);
            }
        }
        return daysInMonthArray;
    }

    @SuppressLint("NewApi")
    private String monthYearFromDate(LocalDate date){
        @SuppressLint({"NewApi", "LocalSuppress"}) DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @SuppressLint("NewApi")
    private String mesConNumero(LocalDate date){
        @SuppressLint({"NewApi", "LocalSuppress"}) DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return date.format(formatter);
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(!dayText.equals("")) {
            Intent i = new Intent(this, HistorialCalendarioSeleccionActivity.class);
            System.out.println(dayText + "/" + mesConNumero(selectedDate));
            i.putExtra("fecha", dayText + "/" + mesConNumero(selectedDate));
            startActivity(i);
        }
        /*if(dayText.equals("")){
            String message = "selected date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }*/
    }
    private void buscarFechasEjerciciosCompletados(){
        SharedPreferences preferencias = getSharedPreferences("fechaEjercicio", MODE_PRIVATE);
        Map mapa = preferencias.getAll();
        todosEjerciciosCompletados = mapa.keySet();
        Object listaTemporal[] = todosEjerciciosCompletados.toArray();
        for(int i = 0; i < todosEjerciciosCompletados.size(); i++){
            listaFinalEjerciciosCompletados.add(listaTemporal[i]);
        }
    }
}