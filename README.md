## Kysymys generaattori!

Kysymysgeneraattori generoi automaattisesti lukiotason kysymyksiä matematiikasta, fysiikasta ja kemiasta käyttäjälleen. Käyttäjiä on useita ja jokaisen käyttäjän tulee rekisteröityä palveluun. Tehdyistä tehtävistä saa pisteitä ohjelmassa.

### Dokumentaatio

[Käyttöohje](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentaatio/kayttoohje.md)

[Määrittelydokumentti](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentaatio/maarittelydokumentti.md)

[Arkkitehtuuri](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri.md)

[Testien dokumentti](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/dokumentaatio/testaus.md)

[Tuntikirjanpito](https://github.com/TheMorshu/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

### Releaset

[Kaikki releaset](https://github.com/TheMorshu/otm-harjoitustyo/releases)

[v 1.0 (loppupalautus)](https://github.com/TheMorshu/otm-harjoitustyo/releases/tag/loppupalautus)

[v 0.1.3 (viikko 6)](https://github.com/TheMorshu/otm-harjoitustyo/releases/tag/viikko6)

[v 0.1.2 (viikko 5)](https://github.com/TheMorshu/otm-harjoitustyo/releases/tag/viikko5)


### Ohjelman hakemisto

[Harjoitustyön hakemisto](https://github.com/TheMorshu/otm-harjoitustyo/tree/master/KysymysGeneraattori)

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

JavaDocin generointu onnistuu suorittamalla seuraava komento maven projektin kansiopolussa:
```
mvn javadoc:javadoc
```
Tämän jälkeen JavaDoc HTML dokumentit löytyvät hakemistosta 

_target/site/apidocs/_

Avaa tiedosto _index_ avataksesi JavaDocin



