# Testausdokumentti

Sovellusta on testattu sekä automatisoiduin yksikkö- ja integraatiotestein, sekä manuaalisesti suoritetuin testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testien sovelluslogiikkaa [(fi.themorshu.logic)](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/KysymysGeneraattori/src/main/java/fi/themorshu/logic) testaavasta osuudesta vastasivat [testiluokat](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/KysymysGeneraattori/src/test/java/fi/themorshu/logic) BasicValuesTest, ChemGentest, GeneratorCoreTest, MathGentest ja PhysGenTest. Automatisoitujen testien pysyväistallennusta [(fi.themorshu.dao)](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/KysymysGeneraattori/src/main/java/fi/themorshu/dao) testaavasta osuudesta vastasivat [testiluokat](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/KysymysGeneraattori/src/test/java/fi/themorshu/dao) DatabaseTest, UserTest ja UsersDaoTest. Kukin testiluokka jäljitteli vastaavannimisen luokan tominnallisuutta mahdollisimman aidosti ja testien tarkoituksena on suorittaa yksikkötestejä erilaisilla simuloiduilla tilanteilla.

Pysyväistallennuksen testaamisessa käytetään erilillistä tietokantaa testien tekemiseen (test.db)

### DAO-luokat

UsersDao-luokan toiminnallisuus testattiin JUnitin avulla käyttämällä erilillistä tietokantaa testien tekemiseen (test.db).

### Testauskattavuus

Käyttöliittymän muodostava koodi poislukien automatisoitujen testien rivikattavuus oli 98% ja haarautumakattavuus 89%.

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/tests.png" width="800">


## Järjestelmätestaus

Järjestelmätestaus onsuoritettu täysin manuaalisesti.

### Asennus ja konfigurointi

Sovellus on ladattu ja sitä käytetään [käyttöohjeen](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentaatio/kayttoohje.md) mukaisesti Windows 10 ja Debian 9 (Linux) ympäristöissä.

Sovellusta on testattu käyttää ensimmäistä kertaa (jolloin sovellus luo ja alustaa uuden tyhjän tietokannan), sekä uudestaan tietokannalla, jossa on jo tietoa. Sovellus on toiminut normaalisti näissä tilanteissa. Tiedon tallentaminen ja lukeminen on onnistunut ongelmitta.

### Toiminnallisuudet

Kaikki [määrittelydokumentissa](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentaatio/maarittelydokumentti.md) esitellyt ominaisuudet on testattu manuaalisesti läpi, eikä ongelmia ole havaittu. Mahdollisia virheellisiä syöttäjä on kokeiltu, eikä ohjelman toiminnallisuus häiriintynyt.


## Sovellukseen jääneet laatuongelmat

Sovelluksen toiminnallisuuteen ei jäänyt suurempia laatuongelmia. Generoitujen kysymystyyppien määrä on kuitenkin suhteellisen pieni. Sovelluksen runko on kuitenkin valmis ja uusien kysymystyyppien lisääminen olisi kuitenkin hyvin helppoa.

