## Kysymys generaattori!

Kysymysgeneraattori generoi automaattisesti lukiotason kysymyksiä matematiikasta, fysiikasta ja kemiasta käyttäjälleen. Käyttäjiä on useita ja jokaisen käyttäjän tulee rekisteröityä palveluun. Tehdyistä tehtävistä saa pisteitä ohjelmassa.

**Releaset**

[Lataa ensimmäinen (pre)release täältä!](https://github.com/TheMorshu/otm-harjoitustyo/releases)

**Hakemisto**

[Harjoitustyön hakemisto](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/KysymysGeneraattori)

**Tuntikirjanpito**

[Tuntikirjanpito](https://github.com/TheMorshu/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

**Dokumentaatio**

[Alusatava määrittelydokumentti](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentaatio/maarittelydokumentti.md)

[Arkkitehtuuri](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri.md)

## Ohjelman suoritus

### NetBeans

Lataa projektsi ja avaa projekti NetBeanssissa. Ohjelma voidaan suorittaa painamalla play nappia (tai F6) NetBeanssissa.

### Jar tiedoston suoritus

Jarin generoinnin jälkeen (lue ohjeet alempana) voidaan jar tiedosto suorittaa suoraan sellaisenaan tuplaklikkaamalla sitä. Vaihtoehtoisesti jar voidaan suorittaa kirjoittamalla komentoriville

```
java -jar KysymysGeneraattori-1.0-SNAPSHOT.jar
```


### Maven

Ohjelma voidaan myös suorittaa suorittamalla seuraava komento terminaalissa:
```
mvn compile exec:java -Dexec.mainClass=fi.themorshu.mainUI.QuestionGeneratorGUI
```
Huom! Komento tulee suorittaa KysymysGeneraattori hakemistossa.

## Komentorivitoiminnot

Huom! Komennot tulee suorittaa KysymysGeneraattori hakemistossa.

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voidaan tarkastella avaamalla tiedosto _/target/site/jacoco/index.html_

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



### Checkstyle

CheckStyle raportti luodaan suorittamalla seuraava komento terminaalissa:

```
mvn jxr:jxr checkstyle:checkstyle
```
Raporttia voidaan tarkastella avaamalla tiedosto _/target/site/checkstyle.html_


### JavaDoc

Kesken!




