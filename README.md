# MathP

Egy projekt a Pannonos tanulmányaimhoz, hogy szerkesztőn belül kóddal tudjak számolni.

## Funkciók

- Vektorok
  - műveletek (+ - * /)
  - diagonál mátrix létrehozása
- Mátrixok
  - műveletek (+ - * /)
  - mátrix szorzás
  - transzponálás
  - determináns számítás
- Gráfok
  - topológiai rendezés
- Egyéb
  - bázis transzformációk
  - futási időt figyelő óra

## Előkészítés használathoz

### Környezet

IntelliJ az ajánlott fejlesztői környezet, de bármi tökéletes, ami tud Kotlin-t futtatni. A projektben a Gradle csomagkezelő található, de nem használom még semmire, szóval tényleg elég bármi, ami a Kotlin-t érti.

### Első futtatás

Ha IntelliJ-ben nyitod meg a projektet, első futtatásnál a main.kt fájl megnyitása után a main függvény melletti futtatás gombot kell használni. Ha a gomb még nem jelent meg, meg kell várni a Gradle build és az indexelés végét. Ha az első futtatás megvolt, már az IntelliJ is tudja hogyan kell indítani a projektet, a jobb felső sarokban lévő gombot is lehet használni.

## Használat

Használd a projektben lévő osztályokat, rajtuk a műveleteket és print-tel ki is írhatod az eredményt.

```kotlin
val vektor1 = Vector(2, 3)
val vektor2 = Vector(5, 4)
println("Vektorok összege:")
println(vektor1 + vektor2)

val mátrix = Matrix(3, // 3 a mátrix szélessége
1, 3, 2,
4, 0, 1)
val mátrixCsakKettes = Matrix(3, 4) { 2.0 } // 3 magas 4 széles mátrix, aminek minden eleme 2
println("Mátrixok szorzata:")
println(mátrix * mátrixCsakKettes)

// Determináns számítás (ezt nem kell kiíratni, a parancs tartalmazza a kiíratást)
Matrix.det(mátrix)
```
