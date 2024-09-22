package org.example;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.upload.MultiFileReceiver;
import com.vaadin.flow.router.Route;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;



import java.awt.*;
import java.util.Collection;

@Route
public class MainView extends VerticalLayout {

    private Grid<Country> grid = new Grid<>(Country.class);

    public MainView() {
        setupHeader("Лабораторная работа №4");
        setupUploadComponent();
        setupGrid();
    }

    private void setupHeader(String title) {
        // Здесь можно настроить стиль заголовка
        add(title);
    }

    private void setupUploadComponent() {
        Button button = new Button("Загрузить файл", event -> {
            // Здесь можно реализовать функциональность для загрузки файла
        });
        add(button);
    }

    private void setupGrid() {
        grid.setColumns("name", "goldMedals", "silverMedals", "bronzeMedals");
        add(grid);
    }

    private void loadFileUsingFileReader(String fileName) {
        List<Country> countries = new ArrayList<>();
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
        List<Country> countries = new ArrayList<>();
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
}