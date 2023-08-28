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
calidad2 = (newQ "oro" 5 7)
calidad3 = (newQ "hierro" 5 4)
calidad4 = (newQ "plata" 9 3.1415)
link12 = (newL ciudad1 ciudad2 calidad2)
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

region1 = foundR region ciudad1
region2 = foundR region1 ciudad2
region3 = foundR region2 ciudad3
region4 = foundR region3 ciudad4 
region5 = foundR region4 ciudad5
region6 = foundR region5 ciudad6
region7 = foundR region6 ciudad7
region8 = foundR region7 ciudad8
region9 = foundR region8 ciudad9
region10 = foundR region9 ciudad10

region11 = linkR region10 ciudad1 ciudad2 calidad1
region12 = linkR region11 ciudad2 ciudad3 calidad2
region13 = linkR region12 ciudad3 ciudad4 calidad2
region14 = linkR region13 ciudad4 ciudad5 calidad2
region15 = linkR region14 ciudad5 ciudad6 calidad3
region16 = linkR region15 ciudad6 ciudad7 calidad4
region17 = linkR region16 ciudad7 ciudad8 calidad4
region18 = linkR region17 ciudad8 ciudad9 calidad1
region19 = linkR region18 ciudad9 ciudad10 calidad1


region20 = tunelR region19 [ciudad1, ciudad2, ciudad3]
region21 = tunelR region20 [ciudad1, ciudad2, ciudad3, ciudad4]



r = [show (foundR region ciudad1) == "Reg [Cit \"g\" (Poi 3 5)] [] []",
 show (linkR region15 ciudad6 ciudad7 calidad4) == "Reg [Cit \"g\" (Poi 3 5),Cit \"h\" (Poi 4 6),Cit \"i\" (Poi 6 8),Cit \"c\" (Poi 4 7),Cit \"d\" (Poi 1 1),Cit \"e\" (Poi 5 7200),Cit \"m\" (Poi 4 6),Cit \"n\" (Poi 24 93),Cit \"k\" (Poi 10 21),Cit \"p\" (Poi 45 271728)] [Lin (Cit \"g\" (Poi 3 5)) (Cit \"h\" (Poi 4 6)) (Qua \"plomo\" 7 1.5),Lin (Cit \"h\" (Poi 4 6)) (Cit \"i\" (Poi 6 8)) (Qua \"oro\" 5 7.0),Lin (Cit \"i\" (Poi 6 8)) (Cit \"c\" (Poi 4 7)) (Qua \"oro\" 5 7.0),Lin (Cit \"c\" (Poi 4 7)) (Cit \"d\" (Poi 1 1)) (Qua \"oro\" 5 7.0),Lin (Cit \"d\" (Poi 1 1)) (Cit \"e\" (Poi 5 7200)) (Qua \"hierro\" 5 4.0),Lin (Cit \"e\" (Poi 5 7200)) (Cit \"m\" (Poi 4 6)) (Qua \"plata\" 9 3.1415)] []",
 show (tunelR region19 [ciudad1, ciudad2, ciudad3]) == "Reg [Cit \"g\" (Poi 3 5),Cit \"h\" (Poi 4 6),Cit \"i\" (Poi 6 8),Cit \"c\" (Poi 4 7),Cit \"d\" (Poi 1 1),Cit \"e\" (Poi 5 7200),Cit \"m\" (Poi 4 6),Cit \"n\" (Poi 24 93),Cit \"k\" (Poi 10 21),Cit \"p\" (Poi 45 271728)] [Lin (Cit \"g\" (Poi 3 5)) (Cit \"h\" (Poi 4 6)) (Qua \"plomo\" 7 1.5),Lin (Cit \"h\" (Poi 4 6)) (Cit \"i\" (Poi 6 8)) (Qua \"oro\" 5 7.0),Lin (Cit \"i\" (Poi 6 8)) (Cit \"c\" (Poi 4 7)) (Qua \"oro\" 5 7.0),Lin (Cit \"c\" (Poi 4 7)) (Cit \"d\" (Poi 1 1)) (Qua \"oro\" 5 7.0),Lin (Cit \"d\" (Poi 1 1)) (Cit \"e\" (Poi 5 7200)) (Qua \"hierro\" 5 4.0),Lin (Cit \"e\" (Poi 5 7200)) (Cit \"m\" (Poi 4 6)) (Qua \"plata\" 9 3.1415),Lin (Cit \"m\" (Poi 4 6)) (Cit \"n\" (Poi 24 93)) (Qua \"plata\" 9 3.1415),Lin (Cit \"n\" (Poi 24 93)) (Cit \"k\" (Poi 10 21)) (Qua \"plomo\" 7 1.5),Lin (Cit \"k\" (Poi 10 21)) (Cit \"p\" (Poi 45 271728)) (Qua \"plomo\" 7 1.5)] [Tun [Lin (Cit \"g\" (Poi 3 5)) (Cit \"h\" (Poi 4 6)) (Qua \"plomo\" 7 1.5),Lin (Cit \"h\" (Poi 4 6)) (Cit \"i\" (Poi 6 8)) (Qua \"oro\" 5 7.0)]]", 
 connectedR region20 ciudad1 ciudad3 == True, linkedR region19 ciudad1 ciudad2 == True, 
 delayR region20 ciudad1 ciudad3 == 1.3468701, availableCapacityForR region20 ciudad1 ciudad2 == 6]