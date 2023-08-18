module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where
import City 
import Quality

-------
--

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
capacityL (Lin ciudad1 ciudad2 (Qua material n demora)) = n
delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin ciudad1 ciudad2 (Qua material n demora)) = demora
