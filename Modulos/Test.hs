import Point
import City
import Quality
import Link
import Tunel 
import Region

ciudad1 = (newC "g" (newP 3 5)) 
ciudad2 = (newC "h" (newP 4 6))
ciudad3 = (newC "i" (newP 6 8))
ciudad4 = (newC "c" (newP 4 7))
ciudad5 = (newC "d" (newP 1 1))
ciudad6 = (newC "e" (newP 5 7200))
ciudad7 = (newC "m" (newP 4 6))
ciudad8 = (newC "n" (newP 24 93))
ciudad9 = (newC "k" (newP 10 21))
ciudad10 = (newC "p" (newP 45 271728))
calidad1 = (newQ "plomo" 7 1.5)
calidad2 = (newQ "oro" 2 7)
calidad3 = (newQ "hierro" 5 4)
calidad4 = (newQ "plata" 3 3.1415)
link12 = (newL ciudad1 ciudad2 calidad1)
link23 = (newL ciudad2 ciudad3 calidad2)
link34 = (newL ciudad3 ciudad4 calidad2)
link45 = (newL ciudad4 ciudad5 calidad2)
link56 = (newL ciudad5 ciudad6 calidad3)
link67 = (newL ciudad6 ciudad7 calidad4)
link78 = (newL ciudad7 ciudad8 calidad4)
link89 = (newL ciudad8 ciudad9 calidad1)
link910 = (newL ciudad9 ciudad10 calidad1)
tunel1234 = (newT [link12, link23, link34])
tunel567 = (newT [link45, link56, link67])
tunel8910 = (newT [link78, link89, link910])
region = newR 

t = [foundR region ciudad1 == region]


-- newL (newC "c" (newP 4 7)) (newC "d" (newP 1 1)) (newQ "plomo" 7 1.5)
-- newL (newC "e" (newP 5 7200)) (newC "f" (newP 35 1)) (newQ "oro" 2 7)
-- newL (newC "g" (newP 8 33)) (newC "h" (newP 5 32)) (newQ "plata" 3 3.1415)