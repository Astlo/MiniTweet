# Projet Web & Cloud and Datastores
Dans le cadre des cours de Web&Cloud en Master Informatique à l'Université Sciences et Techniques de Nantes, nous devions réaliser un mini-twitter à l'aide de Google App Engine.
#### Auteurs
   LE BARS Yannis  
   HUNAULT Marion  
   M1 ALMA 2018-2019  
## Plan
* [Présentation du projet](https://github.com/Astlo/MiniTweet#Presentation)
* [Tests](https://github.com/Astlo/MiniTweet#Tests)
   - [question 1 : Post d'un tweet](https://github.com/Astlo/MiniTweet#Tests#Question_1)  
   - [question 2 : Récupération timeline](https://github.com/Astlo/MiniTweet#Tests#Question_2)  
   - [question 3 : Hashtag](https://github.com/Astlo/MiniTweet#Tests#Question_3) 
* [Performances](https://github.com/Astlo/MiniTweet#Tests)
## Presentation
   Le [sujet] du projet.  
   Le [lien] de l'API.  
## Tests
### Question_1 :
>Sur 30 mesures, combien de temps en moyenne prend le post d’un twitt pour une personne suivie par 100 followers, 1000 followers ou 5000 followers ?

100 foll | 1000 foll | 5000 foll
-------- | --------- | ---------
299 |150| 1024
297 |116| 503
276 |215| 481
184 |219| 462
302 |112| 460
263 |129| 482
268 |159| 381
216 |144| 394
285 |161| 417
260 |126| 381
214 |182| 465
258 |263| 436
141 |114| 517
145 |168| 487
243 |127| 446
267 |116| 392
263 |117| 539
307 |131| 402
227 |210| 544
237 |108| 460
285 |114| 401
173 |170| 295
270 |207| 357
165 |166| 325
232 |112| 421
202 |169| 418
275 |193| 451
295 |144| 504
232 |191| 295
106 |156| 285

##### Resultats
x | 100 | 1000 | 5000 |
------------- | ---: | ----: | ----:
**moyenne en ms** |239,566 | 156,300 | 447,500
**variance** | 2823,840 | 1592,079 | 16676,741
**écart type** | 53,140 | 39,901 | 129,138

### Question_2 :
>Pour chaque configuration du nombre de followers, sur 30 mesures, combien de temps en moyenne pour extraire ses 10 derniers messages,
50 derniers messages et 100 derniers messages ?

#### Configuration 100 followers
10 mes | 50 mes	| 100 mes
------ | ------ | -------
2	|2|	308
2 |2| 2
2	|2| 2
1 |1| 1
2 |2|	2
2	|2|	1
2	|1|	2
2 |10| 1
2 |2| 2
1	|1| 2
2 |2| 1
2	|1| 1
2	|2|	2
1	|2|	1
2	|2|	2
2	|2|	2
2	|2|	1
1	|2|	2
1	|1| 2
1	|2|	1
1	|1|	2
2	|1|	2
1	|2|	1
2	|1|	1
2	|2|	2
2	|1|	1
1	|1|	2
2	|8|	1
1	|2|	1
2	|2|	1

##### Resultats
x | 10 | 50 | 100 |
------------- | ---: | ----: | ----:
**moyenne en ms** |1,666 | 2,133 | 11,733
**variance** | 0,230 | 3,775 | 3131,306
**écart type** | 0,479 | 1,943 | 55,958
#### Configuration 1000 followers
10 mes | 50 mes	| 100 mes
------ | ------ | -------
1	|2|	2
1	|2|	2
1	|2|	2
1	|1|	2
1	|2|	2
1	|1|	1
1	|1|	1
1	|1|	2
1	|1|	1
1	|1|	2
1	|1|	1
2	|1|	2
1	|1|	2
2	|1|	2
1	|1|	1
1	|1|	1
1	|8|	1
1	|1|	1
1	|5|	2
1	|1|	2
2	|1|	1
1	|1|	2
2	|1|	1
2	|1|	1
2	|1|	2
1	|1|	2
2	|1|	2
1	|1|	1
2	|1|	1
2	|2|	1

##### Resultats
x | 10 | 50 | 100 |
------------- | ---: | ----: | ----:
**moyenne en ms** |1,300 | 1,533 | 1,533
**variance** | 0,217 | 2,120 | 0,257
**écart type** | 0,466 | 1,456 | 0,507
#### Configuration 5000 followers
10 mes | 50 mes	| 100 mes
------ | ------ | -------
17	|1|	2
1	|1|	1
1	|1|	1
2	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
2	|2|	1
1	|1|	1
1	|43|	1
2	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
95	|1|	1
1	|1|	1
1	|2|	2
1	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
1	|1|	1
2	|1|	2

##### Resultats
x | 10 | 50 | 100 |
------------- | ---: | ----: | ----:
**moyenne en ms** |4,800 | 2,466 | 1,100
**variance** | 298,717 | 58,671 | 0,093
**écart type** | 17,283 | 7,660 | 0,305

### Question_3
>Sur 30 mesures, combien de temp en moyenne pour extraire les 50 derniers message pour une hashtag concernant 1000 et 5000 messages ?

1000 mes | 5000 mes
-------- | --------
288	|1
1|	1
1|	1
2|	1
1|	1
1|	2
2|	1
2|  2
2|	2
1|	1
2|	2
2|	2
1|	2
1|	1
1|	2
2|	1
1|	2
1|	2
2|	2
1|	1
1|	2
2|	2
1|	1
2|	2
1|	2
1|	2
2|	2
1|	2
2|	1
2|	2

##### Resultats
x | 1000 | 5000 
------------- | ----: | ----: 
**moyenne en ms** |11,000 | 1,600
**variance** | 2737,310 | 0,248
**écart type** | 52,319 | 0,498

### Performances

[sujet]: https://docs.google.com/document/d/1wVf1dWzbmxp5wtJd_I9kAHpke29FpPqe8mPOCv3J1mM/edit?usp=sharing
[lien]: https://minitwettest.appspot.com/
