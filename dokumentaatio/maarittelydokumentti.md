## Määrittelydokumentti

**Sovelluksen tarkoitus**

Sovelluksella on tarkoitus harjoitella lukion matematiikan, fysiikan ja kemian perustehtäviä. Sovellus
generoi käyttäjälleen satunnaisia kysymyksiä vastauksineen, ja käyttäjän on tarkoitus laskea tehtäviin
vastaukset. Käyttäjä voi valita erikseen harjoitteluaiheeksi joko matematiikan, kemian, fysiikan tai useamman
näistä.


**Käyttäjät**

Kirjautumattoman käyttäjän toiminnot ovat seuraavia:
- Pystyy kirjautumaan sisään vanhoilla tunnuksilla
- Pystyy luomaan uudet käyttäjätunnukset
- Pystyy katsomaan tuloslistaa (johon on lsitattu käyttäjät, käyttäjän tekemien tehtävien määrät, sekä pisteet)
- Pystyy lopettamaan ohjelman käytön

Kirjautuneen (normaalikäyttäjä) toiminnot ovat seuraavia:
- Kaikki mitä kirjautumaton käyttäjä pystyy tekemään
- Käyttäjä pystyy valitsemaan itselleen mieluiset tehtävät (matikka, kemia, fysiikka tai sekaisin), jonka jälkeen ohjelma
antaa pelaajalle satunnaisesti generoituja kysymyksiä johon pelaajan tulee vastata ohjeiden mukaisesti
- Käyttäjä voi poistaa oman tilinsä ja/tai nollata pisteensä ja tehdyt tehtävät
- Käyttäjä voi vaihtaa salasanansa


Ylläpitäjän (username: admin, password: admin) toiminnot ovat seuraavia:
- Avautuu erikseen valikko, joka sisältää "admin tooleja", mm. seuraavia: käyttäjätietokannan nollaus, käyttäjien listaus salasanoineen, yksittäisen käyttäjän poisto. Valikosta poistuessa palataan kirjautumisvalikkoon, admin tililal ei siis pysty tekemään tehtäviä.



**Toiminnallisuudet**
- Ohjelma generoi automaattisesti kysymykset ja niihin liittyvät oikeat vastaukset. Käyttäjän tulee antaa vastaus pyydetyssä muodossa. Pisteitä saa oikeista vastauksista.
- (Mahdollinen bonuslisä) Pisteytys toimii vastausajan funktiona


**Rajoitteet**
- Käyttäjien tiedot (nimi, salasana, tehtävien määrä, pisteet) tulee tallentaa ja käsitellä paikallisessa tietokannassa SQLite:llä Dao:n avulla.
- Ohjelma tulee toimia millä tahansa tietokoneella, jossa on Java asennettu (tulee pakata toimivaksi Jar tiedostoksi)
