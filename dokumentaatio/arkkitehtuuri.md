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

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/datamalli.png" width="800">

Kysymyksiä on siis näennäisesti ääretön määrä. Käyttäjän tekemien vastsausten pohjalta tallennetaan tuloksia tietokantaan UsersDao luokan avulla. Ydintoiminnallisuuksista vastaa pääosin luokka GeneratorCore (logiikka), jolla löytyy seuraavat kysymystenhallintaan liittyvät metodit:

- String getQuestion
- String getAnswer
- boolean sendAnswer(String vastaus)

Sovelluksen tietojen tallennuksesta vastaa rajapinnan Dao toteuttava luokka UsersDao.

Koko sovelluksen toiminnasta saa parhaan kuvan luokka/pakkauskaaviosta:

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/luokkakaavio.png" width="800">

## Tietojen pysyväistallennus

### Tiedostot

### Päätoiminnallisuudet

#### Käyttäjän vastatessa oikein kysymykseen

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/sekvenssiKysymysOikein.png" width="800">

#### Käyttäjän vastatessa väärin kysymykseen

<img src="https://raw.githubusercontent.com/TheMorshu/otm-harjoitustyo/master/dokumentaatio/sekvenssiUusiKysymysMat.png" width="800">

#### Muut toiminnallisuudet

## Ohjelman rakenteeseen jääneet puutteellisuudet

### Heikkous 1

### heikkous 2


