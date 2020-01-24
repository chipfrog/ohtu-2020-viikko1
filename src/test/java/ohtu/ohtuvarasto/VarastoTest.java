package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    Varasto virheellinenVarasto;
    Varasto varastoTilavuudellaJaSaldolla;
    Varasto virheellinenVarasto2;
    Varasto varastoLiianTaysi;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        virheellinenVarasto = new Varasto(-5);
        
        varastoTilavuudellaJaSaldolla = new Varasto(10, 5);
        virheellinenVarasto2 = new Varasto(-2, -5);

        varastoLiianTaysi = new Varasto(5, 10);

    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonTilavuusEiVoiOllaNegatiivinen() {
      assertEquals(0, virheellinenVarasto.getTilavuus(), vertailuTarkkuus);
      assertEquals(0, virheellinenVarasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoEiVoiOllaNegatiivinen() {
      assertEquals(0, virheellinenVarasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonAlkusaldolla() {
      assertEquals(5, varastoTilavuudellaJaSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonSaldoEiYlitaTilavuutta() {
      assertEquals(5, varastoLiianTaysi.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisataNegatiivistaMaaraa() {
      varasto.lisaaVarastoon(-5);
      assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisataYliTilavuuden() {
      varasto.lisaaVarastoon(100);
      assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaNegatiivistaMaaraa() {
      assertEquals(0, varasto.otaVarastosta(-5), vertailuTarkkuus);
      
    }

    @Test
    public void varastostaOtetaanKaikkiJosPyydettyMaaraYlittaaSaldon() {
      assertEquals(5, varastoTilavuudellaJaSaldolla.otaVarastosta(100), vertailuTarkkuus);
    }
    @Test
    public void varastonTiedotTulostuvat() {
      String expected = "saldo = " + varastoTilavuudellaJaSaldolla.getSaldo() + ", vielä tilaa " + varastoTilavuudellaJaSaldolla.paljonkoMahtuu();
      assertEquals(expected, varastoTilavuudellaJaSaldolla.toString());
    }


}