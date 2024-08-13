package com.exemplo.automacao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RastreamentoPage extends DriverClass {

    public RastreamentoPage(WebDriver driver) {
        super(driver);
    }

    public void buscarRastreamento(String codigo) throws InterruptedException {
        driver.get("https://rastreamento.correios.com.br/app/index.php");
        Thread.sleep(12000); // insira o captcha

        WebElement campoRastreamento = driver.findElement(By.className("form-control"));
        campoRastreamento.sendKeys(codigo);

        WebElement botaoBuscar = driver.findElement(By.id("b-pesquisar"));
        botaoBuscar.click();
    }

    public String getResultadoRastreamento() {
        WebElement resultado = driver.findElement(By.cssSelector(".msg"));
        return resultado.getText();
    }
}
