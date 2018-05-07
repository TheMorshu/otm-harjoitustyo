# Testausdokumentti

Sovellusta on testattu sekä automatisoiduin yksikkö- ja integraatiotestein, sekä manuaalisesti suoritetuin testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testien sovelluslogiikkaa [(fi.themorshu.logic)](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/KysymysGeneraattori/src/main/java/fi/themorshu/logic) testaavasta osuudesta vastasivat testiluokat BasicValuesTest, ChemGentest, GeneratorCoreTest, MathGentest ja PhysGenTest. Automatisoitujen testien pysyväistallennusta [(fi.themorshu.dao)](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/KysymysGeneraattori/src/main/java/fi/themorshu/dao) testaavasta osuudesta vastasivat testiluokat DatabaseTest, UserTest ja UsersDaoTest. Kukin testiluokka jäljitteli vastaavannimisen luokan tominnallisuutta mahdollisimman aidosti ja testien tarkoituksena on suorittaa yksikkötestejä erilaisilla simuloiduilla tilanteilla.

Pysyväistallennuksen testaamisessa käytetään erilillistä tietokantaa testien tekemiseen (test.db)

### DAO-luokat

UsersDao-luokan toiminnallisuus testattiin JUnitin avulla käyttämällä erilillistä tietokantaa testien tekemiseen (test.db).

### Testauskattavuus

Käyttöliittymän muodostava koodi poislukien automatisoitujen testien rivikattavuus oli 98% ja haarautumakattavuus 89%.

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/tests.png" width="800">


## Järjestelmätestaus

Järjestelmätestaus onsuoritettu täysin manuaalisesti.

### Asennus ja kanfigurointi

Sovellus on ladattu ja sitä käytetään [käyttöohjeen](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentaatio/kayttoohje.md) mukaisesti Windows 10 ja Debian 9 (Linux) ympäristöissä.

Sovellusta on testattu käyttää ensimmäistä kertaa (jolloin sovellus luo ja alustaa uuden tyhjän tietokannan), sekä uudestaan tietokannalla, jossa on jo tietoa) ja sovellus on toiminut normaalisti näissä tilanteissa, sekä tiedon tallentaminen ja lukeminen on onnistunut.

### Toiminnallisuudet

Kaikki [määrittelydokumentissa](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentaatio/maarittelydokumentti.md) esitellyt ominaisuudet on testattu manuaalisesti läpi, eikä ongelmia ole havaittu. Mahdollisia virheellisiä syöttäjä on kokeiltu, eikä ohjelman toiminnallisuus häiriintynyt.


## Sovellukseen jääneet laatuongelmat

### Käyttäjän vaihtaminen kirjautumisvalikossa hieman sekavaa

Toiselle käyttäjälle pystyy kirjautumaan alkuvalikossa (ja nykyiseltä ulos), vaikka olisi jo kirjautunut yhdelle. Tämä tapahtuu kirjoittamalla kirjautumistiedot kenttiin ja painamalla "vanha käyttäjä" nappia. Tämä saattaa olla hieman epäselvää, ja voisi olla käyttäjän kannalta suoraviivaisempaa, jos ohjelmassa olisi erikseen vielä uloskirjautumisnappi.

