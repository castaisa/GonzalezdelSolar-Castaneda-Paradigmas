import Point
import City 
import Quality
import Link 
import Tunel 
import Region
import Control.Exception

ciudad1 = (newC "g" (newP 3 5)) 
ciudad2 = (newC "h" (newP 4 6))
ciudad3 = (newC "i" (newP 6 8))
ciudad4 = (newC "j" (newP 5 1))
link12 = (newL (newC "g" (newP 3 5)) (newC "h" (newP 4 6)) (newQ "hierro" 5 4))
link23 = (newL (newC "h" (newP 4 6)) (newC "i" (newP 6 8)) (newQ "hierro" 5 4))
tunel1 = (newT [link12, link23])
calidad1 = (newQ "hierro" 5 4)


t = [difP (newP 3 5) (newP 4 6) == 1.4142135,
     distanceC ciudad1 ciudad2 == 1.4142135,
     capacityQ calidad1 == 5,
     delayQ calidad1 == 4,
     connectsL ciudad1 link12 == True,
     linksL ciudad1 ciudad2 link12 == True,
     capacityL link12 == 5,
     delayL link23 == 0.70710677,
     connectsT ciudad1 ciudad3 tunel1 == True,
     usesT link23 tunel1 == True,
     delayT tunel1 == 1.0606601
     ] 