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
