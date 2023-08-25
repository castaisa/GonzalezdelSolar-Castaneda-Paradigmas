module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import Point 
import City 
import Quality


------
data Link = Lin City City Quality deriving (Eq, Show)


newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL ciudad1 ciudad2 calidad = Lin ciudad1 ciudad2 calidad

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL ciudad (Lin ciudad1 ciudad2 calidad)  |ciudad == ciudad1 = True
                                                |ciudad == ciudad2 = True
                                                |otherwise = False
linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL ciudadA ciudadB (Lin ciudad1 ciudad2 calidad) |(ciudadA == ciudad1 && ciudadB == ciudad2) 
                                                     ||(ciudadB == ciudad1 && ciudadA == ciudad2) = True
                                                     |otherwise = False
capacityL :: Link -> Int
capacityL (Lin ciudad1 ciudad2 calidad ) = capacityQ calidad

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin ciudad1 ciudad2 calidad) = (distanceC ciudad1 ciudad2) / (delayQ calidad)                                                                                                                                                          

-- capacityL (Lin (Cit "a" (Poi 2 3)) (Cit "n" (Poi 1 5)) (Qua "metal" 4 5))
-- new_P 2 3   new_P 1 5
-- ciudad1 = newC "a" (new_P 2 3)    ciudad2 = newC "b" (new_P 1 5)
-- popo = newQ "popo" 5 6.5
-- newL (newC "a" (new_P 2 3)) (newC "b" (new_P 1 5)) (newQ "popo" 5 6.5)
-- newL (newC "c" (new_P 4 7)) (newC "d" (new_P 1 1)) (newQ "hierro" 2 1.5)
-- newL (newC "e" (new_P 5 7200)) (newC "f" (new_P 35 1)) (newQ "hierro" 1 7)
-- newL (newC "g" (new_P 8 33)) (newC "h" (new_P 5 32)) (newQ "plata" 2 3.1412)

