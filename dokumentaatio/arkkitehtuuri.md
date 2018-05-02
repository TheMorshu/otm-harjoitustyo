# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmeosaista kerrosarkkitehtuuria (sillä poikkeuksella, että UI kontrolloi joissakin tilanteissa suoraan UsersDao oliota). Koodin pakkausrakenne tulee ilmi luokkakaaviosta:

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/luokkakaavio.png" width="800">

Pakkaus fi.themorshu.mainUI sisältää JavaFX:llä toteutetun käyttöliittymän rakentavan koodin.
Pakkaus fi.themorshu.logic sisältää sovelluslogiikan koodin
Pakkaus fi.themorshu.dao sisältää pysyväistallennukseen liittyvän koodin

## Käyttöliittymä

Käyttöliittymä sisältää 5 erillistä näkymää:

- Aloitusnäkymä (käyttäjän luominen/käyttäjän kirjautuminen/tehtävätyypin valinta)
- Tulosnäkymä (engl. hiscore)
- Käyttäjäkohtainen asetusnäkymä (kirjautumisen jälkeen, jos esim. käyttäjä haluaa vaihtaa salasanaansa)
- Admin hallintanäkymä (sisäänkirjautuminen nimellä ja salasanalla admin ja admin)
- Kysymys- ja vastausnäkymä

Jokainen näkymistä on toteutettu Scene oliona. Scene oliot ja käyttöliittymä on rakennettu luokassa fi.themorshu.mainUI.QuestionGeneratorGUI

Käyttöliittymä on pyritty (mahdollisimman hyvin) erottamaan sovelluslogiikasta ja sovelluslogiikka on yritetty sijoittaa mahdollisimman hyvin GeneratorCore (vanha nimi Generator) luokkaan sekä Gen rajapinnan toteuttaviin kysymysgeneraattoreihin. Lisäksi pysyväistallennusta hallitseva logiikka on sisällytetty luokkaan UsersDao. Sovelluslogiikkaa voitaisiin kuitenkin eritellä käyttöliittymän rakentavasta koodista erilleen vielä lisää. 

Sovelluksen esittämät kysymykset haetaan GeneratorCore (vanha nimi Generator) luokan avulla Gen rajapinnan toteuttavilla luokilla ja käyttäjän antamien vastausten oikeellisuuden tarkistus tehdään GeneratorCore (vanha nimi Generator) luokassa. Muutokset tilanteisiin tallennetaan tietokantaa UsersDao luokan avulla.

## Sovelluslogiikka

Sovelluslogiikan datamallin muodostaa luokka User, sekä luokka GeneratorCore, joka vastaa kysymyksien generoimisesta. Erillistä luokkaa kysymyksille ei ole.

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/datamodel.png" width="800">

Kysymyksiä on siis näennäisesti ääretön määrä. Käyttäjän tekemien vastsausten pohjalta tallennetaan tuloksia tietokantaan UsersDao luokan avulla. Ydintoiminnallisuuksista vastaa pääosin luokka GeneratorCore (logiikka), jolla löytyy seuraavat kysymystenhallintaan liittyvät metodit:

- String getQuestion
- String getAnswer
- boolean sendAnswer(String vastaus)

Sovelluksen tietojen tallennuksesta vastaa rajapinnan Dao toteuttava luokka UsersDao.

Koko sovelluksen toiminnasta saa parhaan kuvan luokka/pakkauskaaviosta:

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/luokkakaavio.png" width="800">

## Tietojen pysyväistallennus

Pakkaus fi.themorshu.dao sisältää kaikki pysyväistallennukseen liittyvät luokat. Luokan Database avulla rajapinnan dao toteuttava luokka UsersDao suorittaa kaiken sovelluksen tarvitseman tallennuksen tietokantaan users.db. 

UsersDao sisältää tallennuksen kannalta oleellisen logiikan. Esimerkiksi jos halutaan tarkistaa, onko jo tietynniminen käyttäjä tallennettu teitokantaan, voidaan tieto tarkistaa Usersdao luokan metodilla Boolean checkContainsName(String name). UsersDao luokan avulla voidaan myös tarkistaa sisäänkirjautumisen oikeellisuus metodilla User verifyLogin(String username, String password).

### Tiedostot

Sovellus tallentaa käyttäjien tiedot (nimi, salasana, vastattujen kysymysten määrä, oikeiden vastausten määrä) tiedostoon users.db tietokantatauluun Users. Tietokantataulu on luotu SQLitellä seuraaavanlaisesti (käyttäjän ei tarvitse huolehtia tästä, ohjelma tekee kaiken):
```
CREATE TABLE Users (id integer PRIMARY KEY, name varchar(200), password varchar (200), questions integer, right integer);
```
Vaikka taulussa on määritelty erikseen pääavain (id integer), sovellus pitää käyttäjänimeä (name) kaikille käyttäjille uniikkina, joten voidaan todeta, että myös name:a voidaan käyttää primary keynä. Sovellus ei siis varsinaisesti hyödynnä missään varsinaista id:tä.

### Päätoiminnallisuudet

Alla on esitetty kaksi esimerkkitapausta ohjelman toiminnallisuudesta sekvenssikaaviona:

####


#### Käyttäjän vastatessa oikein kysymykseen

Kun käyttäjä on kirjoittanut vastausnäkymään vastauksen (joka on oikein) ja käyttäjä painaa "Lähetä vastaus!" painiketta.

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/sekvenssiKysymysOikein.png" width="800">

Kun GeneratorCore toteaa, että käöyttäjän antama vastaus on oikein, antaa se UsersDao:lle käskyn lisätä kyseiselle käyttäjälle yhden oikeinmerkin. Käyttäjä jaa käyttöliittymään vastaavaa palautetta.

#### Uuden kysymyksen noutaminen

Kun käyttäjä on vastannut kysymykseen (sitten oikein tai väärin) pyytää käyttöliittymä uuden generoidun kysymyksen GeneratorCorelta. Tässä esimerkissä on kysymystyypiksi valittu matematiikkaan liittyvät kysymykset. GeneratorCore pyytää Gen rajapinnan toteuttavalta MathGen luokan ainoalta oliolta uuden kysymyksen ja vastauksen, joista kysymys palautetaan käyttöliittymään. Myöhemmin käyttäjän uudelleen vastatessa kysymykseen, verrataan käyttäjän antamaa vastausta MathGenin palauttamaan generoituun vastaukseen (tätä ei ole sekvenssikaaviosta)

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/sekvenssiUusiKysymysMat.png" width="800">

#### Muut toiminnallisuudet

Kaikki muut toiminnallisuude noudattavat samaa periaatetta. Käyttöliittymästä käyttäjä "antaa käskyjä". Käskyn tyypistä joko GeneratorCore (kysymykset, tarkistus ja logiikka) tai UsersDao (tallennus, käyyäjitnhallinta) hoitaa varsinaisen toiminnallisuuden ja lähettää käyttöliittymälle informaatiota takaisin.

## Ohjelman rakenteeseen jääneet puutteellisuudet

### Käyttöliittymän heikkoudet

Käyttöliittymä on yleisesti sovelluksessa siisti ja näppärä. Ongelmana on kuitenkin se, että tietyissä tilanteissa uusien nappien/kenttien lisääminen siirtää hieman jo olemassaolevia objekteja, jolloin käyttäjälle voi tulla "hyppivä" kokemus sovellusken käytöstä. Tämä ongelma on korjattavissa. Lisäksi sovelluslogiikkaa pystyttäisiin hieman irroittamaan lisää käyttöliittymän rakentavasta koodista.

### Sovelluslogiikan pirstoutuminen

Vaikka sovelluslogiikka onkin suurimmaksi osaksi irroitettu käyttöliittymän rakentavasta koodista, on se silti pirstoutunut kahden luokan, UsersDao:n ja GeneratorCore:n välille. Osa UsersDao:n toiminnallisuuksista pystyttäisiin helpohkosti linkittämään GeneratorCore:lle, jolloin käyttöliittymän koodissa voitaisiin kutsua pelkästään GeneratorCore:n metodeja ja logiikka olisi ainakin näenäisesti keskitetty pelkästään sinne. Tämä voitaisiin käytännössä toteuttaa niin, että GeneratorCoree:n lisättäisiin UsersDao:n metodeja kutsuvia metodeja, jolloin toiminnallisuus ja palutusarvor pysyisivät täysin samoina.


