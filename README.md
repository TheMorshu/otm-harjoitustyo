## Kysymys generaattori!

**HUOM! Muutin työni kansiorakennetta fi.TheMorshu.... --> fi.themorshu**
**Tämän vuoksi ennen 10.4 tehdyt viikon 3 palautukset poistuivat, ja uudet tulivat kansioon fi.themorshu... (pvm 12.4)**

Kysymysgeneraattori generoi automaattisesti lukiotason kysymyksiä matematiikasta, fysiikasta ja kemiasta käyttäjälleen. Käyttäjiä on useita ja jokaisen käyttäjän tulee rekisteröityä palveluun. Tehdyistä tehtävistä saa pisteitä ohjelmassa.

**Hakemisto**

[Harjoitustyön hakemisto](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/KysymysGeneraattori)

**Tuntikirjanpito**

[Tuntikirjanpito](https://github.com/TheMorshu/otm-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

**Dokumentaatio**

[Alusatava määrittelydokumentti](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentointi/maarittelydokumentti.md)


## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voidaan tarkastella avaamalla tiedosto _target/site/jacoco/index.html_

Huom! Joskus testikattavuuden generoinnissa on ongelmia. Tällöin kannattaa suorittaa terminaalissa seuraavat komennot annetussa järjestyksessä:

```
mvn clean
mvn package
mvn jacoco:report
```


### Jarin generointi

Komennolla:

```
mvn package
```

Jolloin hakemistoon _target_ generoituu jar tiedosto _KysymysGeneraattori-1.0-SNAPSHOT.jar_



### JavaDoc

Kesken!

### Checkstyle

Kesken!





