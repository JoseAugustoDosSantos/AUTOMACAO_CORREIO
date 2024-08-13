package com.exemplo.automacao;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TesteSelenium {

    private WebDriver driver;
    private CepSearchPage cepSearchPage;
    private RastreamentoPage rastreamentoPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        cepSearchPage = new CepSearchPage(driver);
        rastreamentoPage = new RastreamentoPage(driver);
    }

    @Test
    public void testBuscarCepA() throws InterruptedException {
        cepSearchPage.buscarCep("80700000");
        String resultado = cepSearchPage.getResultadoBusca();
        //assert por xpath
        Assert.assertTrue(resultado.contains("Dados não encontrado"), "O resultado não contém 'Dados não encontrado'");
        cepSearchPage.voltar();
    }

    @Test
    public void testBuscarCepB() throws InterruptedException {
        cepSearchPage.buscarCep("01013-001");
        Thread.sleep(2000);
        WebElement resultado = driver.findElement(By.xpath("//td[@data-th='Logradouro/Nome'][contains(text(),'Rua Quinze de Novembro - lado ímpar')]"));
        Assert.assertTrue(resultado.getText().contains("Rua Quinze de Novembro - lado ímpar"), "O CEP está incorreto, o esperado é 'Rua Quinze de Novembro - lado ímpar.'");
        Thread.sleep(2000);
        WebElement botaoVoltar = driver.findElement(By.id("btn_nbusca"));
        //assert por id
        Assert.assertTrue(botaoVoltar.isDisplayed(), "O botão de Voltar não está visível");

        cepSearchPage.voltar();
    }

    @Test
    public void testBuscarRastreamentoIncorreto() throws InterruptedException {
        rastreamentoPage.buscarRastreamento("SS987654321BR");
        Thread.sleep(2000);
        String resultado = rastreamentoPage.getResultadoRastreamento();
        //assert por cssSelector
        Assert.assertTrue(resultado.contains("Objeto não encontrado na base de dados dos Correios."), "O código de rastreamento está correto, o esperado é 'Código inválido'");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
