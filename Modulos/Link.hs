module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import Point 
import City 
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality = Lin city1 city2 quality

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin city1 city2 quality) = city == city1 || city == city2

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL cityA cityB (Lin city1 city2 quality) = (cityA == city1 && cityB == city2) || (cityB == city1 && cityA == city2)
                                                     
capacityL :: Link -> Int
capacityL (Lin city1 city2 quality ) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin city1 city2 quality) = (distanceC city1 city2) / (delayQ quality)