module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Point
import City
import Quality
import Link


data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun  

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun listL) = ((connectsL city1 (head listL)) && not (connectsL city1 (listL !! 1)) && (connectsL city2 (last listL)) && not (connectsL city2 ( last (init listL))))
                                   || ((connectsL city2 (head listL)) && not (connectsL city2 (listL !! 1)) && (connectsL city1 (last listL)) && not (connectsL city1 ( last (init listL))))
                                   
usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun listL) = elem link listL

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun listL) = foldr (\link acc -> delayL link + acc) 0.0 listL