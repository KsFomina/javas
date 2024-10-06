package org.example;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Route
public class MainView extends VerticalLayout {

    private Grid<Country> grid = new Grid<>(Country.class, false);
    private ArrayList<Country> countries = new ArrayList<>();
    public MainView() {
        setupHeader("Лабораторная работа №4");
        setupUploadComponents();
        setupGrid();
        getChampion();
        comparison();
    }
    private void setupHeader(String title) {
        // Здесь можно настроить стиль заголовка
        add(title);
    }
    private void setupUploadComponents() {
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Выберите способ чтения файла:");
        radioGroup.setItems("Стандартное чтение", "Чтение в функциональном стиле");
        add(radioGroup);
        com.vaadin.flow.component.textfield.TextField textField = new com.vaadin.flow.component.textfield.TextField();
        Button button = new Button("Загрузить файл", event -> {
            grid.setItems(Collections.emptyList());
            countries.clear();
            String filename=textField.getValue();
            if (filename.length()==0){
                textField.setValue("Введите имя файла!");
            }
            else {
                String selectedOption = radioGroup.getValue();
                if (selectedOption=="Стандартное чтение") loadFileUsingFileReader(filename);
                else loadFileUsingFiles(filename);
            }

        });
        add(textField);
        add(button);
    }
    private void setupGrid() {
        grid.setColumns("name", "goldMedals", "silverMedals", "bronzeMedals");
        add(grid);
    }
    private void loadFileUsingFileReader(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Неверный формат файла");
                }
                String countryName = parts[0];
                int gold = Integer.parseInt(parts[1]);
                int silver = Integer.parseInt(parts[2]);
                int bronze = Integer.parseInt(parts[3]);
                countries.add(new Country(countryName, gold, silver, bronze));
            }
            grid.setItems(countries);
        } catch (IOException e) {
            Notification.show("Ошибка чтения файла: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            Notification.show(e.getMessage());
        }
    }
    private void loadFileUsingFiles(String fileName) {
        try {
            Files.lines(Path.of(fileName)).forEach(line -> {
                String[] parts = line.split(" ");
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Неверный формат файла");
                }
                String countryName = parts[0];
                int gold = Integer.parseInt(parts[1]);
                int silver = Integer.parseInt(parts[2]);
                int bronze = Integer.parseInt(parts[3]);
                countries.add(new Country(countryName, gold, silver, bronze));
            });
            grid.setItems(countries);
        } catch (IOException e) {
            Notification.show("Ошибка чтения файла: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            Notification.show(e.getMessage());
        }
    }

    private void getChampion(){
        Text text1=new Text("");
        HorizontalLayout layout=new HorizontalLayout();
        Button button = new Button("Больше золотых медалей:", event -> {
            Collections.sort(countries, Comparator.comparingInt(Country::getGoldMedals).reversed());
            text1.setText("Наибольшее количество золотых медалей получила: "+ countries.get(0).getName());

        });
        layout.add(button,text1);
        add(layout);
    }
    private void comparison(){
        HorizontalLayout layout1=new HorizontalLayout();
        HorizontalLayout layout2=new HorizontalLayout();
        TextField country1=new TextField();
        Text text2=new Text(" и ");
        Text text3=new Text("");
        TextField country2=new TextField();
        Button button = new Button("Больше золотых медалей у ", event -> {
            Country C1=new Country(country1.getValue(),0,0,0);
            Country C2=new Country(country2.getValue(),0,0,0);
            int gold1=countries.get(countries.indexOf(C1)).getGoldMedals();
            int gold2=countries.get(countries.indexOf(C2)).getGoldMedals();
            if (gold1>gold2){
                text3.setText("Наибольшее количество золотых медалей получила: "+ country1.getValue());}
            else if (gold1<gold2) {
                text3.setText("Наибольшее количество золотых медалей получила: "+ country2.getValue());
            }
            else {
                text3.setText("Обе страны получили равное число медалей");
            }
            add(text3);


        });
        layout1.add(country1,text2,country2);
        add(layout1);
        layout2.add(button,text3);
        add(layout2);
    }
}