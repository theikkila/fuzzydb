# Päiväkirja


# Viikko 1

Ensimmäisellä viikolla tutustuin lyhyeesti __Okazakin__ artikkeliin _Simple and Efficient Algorithm for Approximate Dictionary Matching_ ja toteutin osatoteutuksen algoritmista (N-gram hajoittamisen). Tuloksena ekan viikon jälkeen erittäin raskas indeksointiprosessi, mutta samalla erittäin nopea haku vaikka worst case aikavaativuus on vielä lähes **O(n)**

Ohjelman varsinainen runko ei ole vielä Githubissa vaan sen sijaan repositorio sisältää lähinnä muutaman luokan jolla ideaa on lähetty testaamaan. Varsinaiseen palvelimeen olen suunnitellut käyttäväni Akan aktoreita, jolloin samanaikainen suorittaminen ei tule ongelmaksi. (Tärkeää palvelimissa, mutta ei vielä tässä vaiheessa)

Opettelin tällä viikolla käyttämään Akan aktoreita.

Vaikein asia oli syventyä erilaisiin _string approximate matching_ algoritmeihin ja valita niistä omaan käyttöön sopivin.

Seuraavaksi aion toteuttaa koko artikkelissa kuvatun algoritmin käyttäen Scalan standardikirjastoa apuna.

# Viikko 2

Omien työkiireideni takia en juuri ehtinyt kehittää työtä eteenpäin.


# Viikko 3

Toteutin suurimman osan algoritmiin tarvittavista tietorakenteista (HashMapin ja Binäärikeon) sekä muutamia aputietorakenteita. Sain naiivin AllScan-ratkaisun toimimaan ja löytämään oikeita tuloksia.

Kommentoin koodia paljon ja kirjoitin 53 testiä, jolloin testikattavuus on lähes 100%, huomioitavaa on kuitenkin etten ole vielä konfiguroinut testikattavuutta mittaavia kirjastoja ja tämä on puhdasta mututuntumaa.

Ensi viikoksi tarkoituksena on implementoida __CPMerge__-algoritmi ja päästä vihdoin haluttuihin tuloksiin tehokkuudessa.