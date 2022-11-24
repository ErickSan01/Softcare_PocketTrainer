package com.softcare.pockettrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class HistorialCalendarioActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private ArrayList<Object> listaFinalEjerciciosCompletados = new ArrayList<>();
    private ArrayList<Object> listaDiasSeleccionados = new ArrayList<>();
    private String fechaActual = "";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_calendario);

        //Botón de retroceder
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        obtenerFechaActual();
        buscarFechasEjerciciosCompletados();
        buscarDiasSeleccionados();

        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();

        Button btnLeft = findViewById(R.id.buttonLeft);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = selectedDate.minusMonths(1);
                setMonthView();
            }
        });

        Button btnRight = findViewById(R.id.buttonRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = selectedDate.plusMonths(1);
                setMonthView();
            }
        });

        Button btn_historial = findViewById(R.id.btn_historial);
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
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this, listaFinalEjerciciosCompletados, listaDiasSeleccionados, fechaActual);
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
    }
    private void buscarFechasEjerciciosCompletados(){
        SharedPreferences preferencias = getSharedPreferences("fechaEjercicio", MODE_PRIVATE);
        Map mapa = preferencias.getAll();
        Set<String> todosEjerciciosCompletados;
        todosEjerciciosCompletados = mapa.keySet();
        Object listaTemporal[] = todosEjerciciosCompletados.toArray();

        for(int i = 0; i < todosEjerciciosCompletados.size(); i++){
            listaFinalEjerciciosCompletados.add(listaTemporal[i]);
        }
    }

    private void buscarDiasSeleccionados(){
        SharedPreferences preferencias = getSharedPreferences("dias", MODE_PRIVATE);
        Map mapa = preferencias.getAll();
        Boolean lunes = Boolean.parseBoolean(""+mapa.get("lunes"));
        Boolean martes = Boolean.parseBoolean(""+mapa.get("martes"));
        Boolean miercoles = Boolean.parseBoolean(""+mapa.get("miercoles"));
        Boolean jueves = Boolean.parseBoolean(""+mapa.get("jueves"));
        Boolean viernes = Boolean.parseBoolean(""+mapa.get("viernes"));
        Boolean sabado = Boolean.parseBoolean(""+mapa.get("sabado"));
        Boolean domingo = Boolean.parseBoolean(""+mapa.get("domingo"));
        if(lunes){
            listaDiasSeleccionados.add("lunes");
        }
        if(martes){
            listaDiasSeleccionados.add("martes");
        }
        if(miercoles){
            listaDiasSeleccionados.add("miercoles");
        }
        if(jueves){
            listaDiasSeleccionados.add("jueves");
        }
        if(viernes){
            listaDiasSeleccionados.add("viernes");
        }
        if(sabado){
            listaDiasSeleccionados.add("sabado");
        }
        if(domingo){
            listaDiasSeleccionados.add("domingo");
        }
    }

    private void obtenerFechaActual(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        System.out.println("sisisisxdfsd: "+formatter.format(date));
        fechaActual = formatter.format(date);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}