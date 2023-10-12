	Kako se pišejo razredi?

Če v primarni konstruktor določimo spremenljivki val lastnost bo samo za branje, če ji določimo var, jo lahko še naknadno spreminjamo po tem ko se njena vrednost dodeli.
Dedujemo z sintakso A(ime:tip) : B(ime). Če hočemo metodo iz glavnega razreda povoziti, jo moremo v glavnem razredu definirati kot open fun.

	Kako se pišejo lastnosti?

Lastnosti so zapisane že v konstruktorju.
Lahko še pišemo dodatne lastnosti, ki se pišejo kot normalne spremenljivke npr: var fullName:String
Še get() in set() metode ne pišemo, spremenljivka dobi privzet getter in setter. Lahko pa svojega napišemo po načinu:
var <propertyName>[: <PropertyType>] [= <property_initializer>]
[<getter>]
[<setter>]

	Kako se pišejo konstante?

Konstane določimo z const. Če uporabimo const moramo uporabiti tudi val. Torej const val a = 12. Const uporabimo za spremenljivke katerih vrednost se ve ob času compile-anja.

	Kako se pišejo metode?

Metode pišemo po načinu fun <ime>(<parametri>): <tip_vracanja>. Tip vracanja je lahko Unit, če metoda ne vrača nobene vrednosti. V istem primeru lahko Unit tudi izpustimo. Lahko pišemo tudi lambda metode npr. val ime:(Int,Int) -> Int = {i,j -> i+j}
Če ima metode en parameter, se na njega lahko sklicujemo z it.
Tipom(Int, String, Double) lahko dodamo razširitvene metode:
fun Int.isOdd() = this % 2 == 1
print(3.isOdd())