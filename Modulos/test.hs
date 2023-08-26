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
link12 = (newL (newC "g" (newP 3 5)) (newC "h" (newP 4 6)) (newQ "hierro" 5 4))
link23 = (newL (newC "h" (newP 4 6)) (newC "i" (newP 6 8)) (newQ "hierro" 5 4))
tunel1 = (newT [link12, link23])



--t = [ testF (fallo (print (result 3 ))),
--      testF (fallo (print (result 8 ))) ]

t = [difP (newP 3 5) (newP 4 6) == 1.4142135,
     distanceC (newC "g" (newP 3 5)) (newC "h" (newP 4 6)) == 1.4142135,
     capacityQ (newQ "hierro" 5 4) == 5,
     delayQ (newQ "hierro" 5 4) == 4,
     connectsL (newC "g" (newP 3 5)) (newL (newC "g" (newP 3 5)) (newC "h" (newP 4 6)) (newQ "hierro" 5 4)) == True,
     linksL (newC "g" (newP 3 5)) (newC "h" (newP 4 6)) (newL (newC "g" (newP 3 5)) (newC "h" (newP 4 6)) (newQ "hierro" 5 4)) == True,
     capacityL (newL (newC "g" (newP 3 5)) (newC "h" (newP 4 6)) (newQ "hierro" 5 4)) == 5,
     delayL (newL (newC "g" (newP 3 5)) (newC "h" (newP 4 6)) (newQ "hierro" 5 4)) == 0.35355338,
     connectsT ciudad1 ciudad3 (newT [link12, link23]) == True,
     usesT link23 tunel1 == True,
     delayT tunel1 == 1.0606601
     ] 
