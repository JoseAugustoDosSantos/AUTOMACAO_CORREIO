package com.exemplo.automacao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CepSearchPage extends DriverClass {

    public CepSearchPage(WebDriver driver) {
        super(driver);
    }

    public void buscarCep(String cep) throws InterruptedException {
        driver.get("https://buscacepinter.correios.com.br/app/endereco/index.php");
        Thread.sleep(12000); // insira o captcha

        WebElement campoCep = driver.findElement(By.id("endereco"));
        campoCep.sendKeys(cep);

        WebElement botaoBuscar = driver.findElement(By.id("btn_pesquisar"));
        botaoBuscar.click();
    }

    public String getResultadoBusca() throws InterruptedException  {
        Thread.sleep(2000);
        WebElement resultado = driver.findElement(By.xpath("//h6[contains(.,'Dados não encontrado')]"));
        return resultado.getText();
    }

    public void voltar() throws InterruptedException {
        WebElement botaoVoltar = driver.findElement(By.id("btn_nbusca"));
        botaoVoltar.click();
        Thread.sleep(2000);
    }
}
