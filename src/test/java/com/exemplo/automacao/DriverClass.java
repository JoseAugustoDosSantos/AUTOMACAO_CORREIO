package com.exemplo.automacao;

import org.openqa.selenium.WebDriver;

public class DriverClass {
    protected WebDriver driver;

    public DriverClass(WebDriver driver) {
        this.driver = driver;
    }
}
