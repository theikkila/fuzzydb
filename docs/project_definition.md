# Määrittelydokumentti

Tämä projekti on toteutettu osana tietorakenteiden ja algoritmien harjoitustyötä.

FuzzyDB on tietokantapalvelin joka tarjoaa erittäin nopeita tekstien hakutoimintoja.
FuzzyDB ei pyri eksakteihin vastaavuuksiin vaan palauttamaan tehokkaasti myös hieman samankaltaiset. Sovellutuksia tälle tietokannalle ovat esimerkiksi autocorrect-toiminto tai esimerkiksi muut automaattisen täytön sovellukset.

Tavoitteena on että nykyaikaisella koneella saavutettaisiin 10M sanan joukosta etsiminen alle 100ms ajassa. Pohjana toteutukselle toimii __Naoaki Okazakin__ teksti _Simple and Efficient Algorithm for Approximate Dictionary Matching_
