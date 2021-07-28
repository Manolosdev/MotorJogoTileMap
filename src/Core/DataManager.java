/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

/**
 *
 * @author Manolo
 */
public class DataManager {

    private File file;
    Properties properties;

    public DataManager(String fileName) throws IOException {
        this.properties = new Properties();
        this.file = new File(fileName);
        if (this.file.exists()) {
            load();
        }
    }

    public DataManager(URI fileURI) throws IOException {
        this.properties = new Properties();
        this.file = new File(fileURI);
        if (this.file.exists()) {
            load();
        }
    }

    public void load() throws IOException {
        this.properties.load(new FileInputStream(this.file));
    }

    public void save() {
        try {
            this.properties.store(new FileOutputStream(this.file), null);
        } catch (IOException ex) {
            System.out.println("Erro: /n" + ex);
        }
    }

    public void write(String propertyName, String value) {
        this.properties.setProperty(propertyName, value);
    }

    public void write(String propertyName, int value) {
        properties.setProperty(propertyName, String.valueOf(value));
    }

    public void write(String propertyName, float value) {
        properties.setProperty(propertyName, String.valueOf(value));
    }

    public void write(String propertyName, boolean value) {
        properties.setProperty(propertyName, String.valueOf(value));
    }

    public String read(String propertyName, String field) {
        return properties.getProperty(propertyName);
    }

    public int read(String propertyName, int field) {
        try {
            return Integer.valueOf(properties.getProperty(propertyName));
        } catch (Exception e) {
            return 0;
        }
    }

    public float read(String propertyName, float field) {
        return Float.valueOf(properties.getProperty(propertyName));
    }

    public boolean read(String propertyName, boolean field) {
        return Boolean.valueOf(properties.getProperty(propertyName));
    }

}
