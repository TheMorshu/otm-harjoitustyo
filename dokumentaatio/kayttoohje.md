## Käyttöohje

Lataa uusin release [täältä](https://github.com/TheMorshu/otm-harjoitustyo/releases) (jar tiedosto). 
Vaihtoehtoisesti voit cloonata repositorion githubista, ja generoida itse jar tiedoston tämän jälkeen ([ohjeet](https://github.com/TheMorshu/otm-harjoitustyo/readme.md))

### Konfigurointi

Ohjelma käyttää users.db tietokantatiedostoa käyttäjien ja heidän pisteidensä tallentamiseen. Tietokantatiedostoa ei tarvitse konfiguroida mitenkään erikseen,
vaan ohjelma luo sen automaattisesti samaan hakemistoon, missä .jar tiedosto on, mikäli tietokantatiedostoa ei ole jo olemassa.

### Ohjelman käynnistäminen

Ohjelma käynnistetään komentoriviltä/terminaalista seuraavalla komennolla:

```
java -jar tiedostonimi.jar
```
Lisäksi ainakin windows tietokoneilla voi jar tiedoston käynnistää suoraan tuplaklikkaamalla

**HUOM! Tiedostonimi voi poiketa releasesta riippuen. Järjenkäyttö on tässä sallittua**

### Aloitusnäkymä ja kirjautuminen

Kun ohjelma käynnistyy, on sisäänkirjausnäkymä seuraavanlainen:
<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/login.png" width="400">
tässä näkymässä voi käyttäjä luoda itselleen tilin salasanoineen, tai kirjautua sisään jo vanhoilla tunnuksillaan.
Ennen varsinaista sisäänkirjautumista tai tilin luomista voi käyttäjä kuitenkin katsoa hiscoreja, jonne on listattuna kaikki käyttäjät pisteineen:
<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/hiscore.png" width="400">
Tässä esimerkissä kukaan käyttäjistä ei ole kuitenkaan vastannut kysymyksiin.

### Sisäänkirjautumisen jälkeen

Sisäänkirjautumisen jälkeen näyttää näkymä seuraavalta:
<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/loggedin.png" width="400">
Mikäli käyttäjä haluaa vaihtaa esimerkiksi salasanaansa tai nollata tuloksensa, voi hän muuttaa asetuksiaan painamalla sopivaa nappia, jolloin käyttäjä siirtyy seuraavanlaiseen valikkonäkymään:
<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/usersettings.png" width="400">
Oletetaan nyt kuitenkin, että käyttäjä on tullut tekemään fysiikan harjoituksia. hän valitsee tehtäviksi fysiikan kysymykset, jolloin ohjelma generoi hänelle automaattisesti fysiikkaan liittyviä kysymyksiä:
<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/question.png" width="400">
Käyttäjä saa pisteitä sitä mukaa, kun hän vastaa oiekin kysymyksiin. Kaikista vastatuista kysymyksistä merkataan myös tieto ylös.


### Admin työkalut

Joskus voi tulla tilanne, että käyttäjä on esimerkiksi ollut vilpillinen tai vaikkapa koko tietokanta joudutaan nollaamaan. Tätä varten on olemassa pääkäyttäjän (admin) työkalut. Admin työkalut saadaan käyttöön normaalisti kirjautumalla käyttäjänimellä admin ja salasanalla admin. Huom! Admin ei ole normaali käyttäjä, joten admin tilillä ei voi tehdä tehtäviä.
<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/admin.png" width="400">



