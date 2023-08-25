module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Data.List

import Point
import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT listL = Tun listL 

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT ciudad1 ciudad2 (Tun listL) | ((connectsL ciudad1 (head listL)) == True && (connectsL ciudad1 (listL !! 1)) == False && (connectsL ciudad2 (last listL)) == True && (connectsL ciudad2 ( last (init listL)) == False ))
                                      || ((connectsL ciudad2 (head listL)) == True && (connectsL ciudad2 (listL !! 1)) == False && (connectsL ciudad1 (last listL)) == True && (connectsL ciudad1 ( last (init listL)) == False )) = True
                                      | otherwise = False
usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT linkA (Tun listL) = elem linkA listL

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun listL) = foldr (\link acc -> delayL link + acc) 0.0 listL

enoughcapacity :: Link -> Tunel -> Bool
enoughcapacity link tunel = capacityL link >= 1

   
elembyelem :: a -> [b]-> [b] 
elembyelem a [] = []
elembyelem (x:xs) =  a ()
                    |elembyelem xs


-- capacityL (Lin (Cit "a" (Poi 2 3)) (Cit "n" (Poi 1 5)) (Qua "metal" 4 5))
-- new_P 2 3   new_P 1 5
-- ciudad1 = newC "a" (new_P 2 3)    ciudad2 = newC "b" (new_P 1 5)
-- popo = newQ "popo" 5 6.5
-- newL (newC "a" (new_P 2 3)) (newC "b" (new_P 1 5)) (newQ "popo" 5 6.5)
-- newL (newC "c" (new_P 4 7)) (newC "d" (new_P 1 1)) (newQ "hierro" 2 1.5)
-- newL (newC "e" (new_P 5 7200)) (newC "f" (new_P 35 1)) (newQ "hierro" 1 7)
-- newL (newC "g" (new_P 8 33)) (newC "h" (new_P 5 32)) (newQ "plata" 2 3.1412)
-- newT [(newL (newC "a" (new_P 2 3)) (newC "b" (new_P 1 5)) (newQ "popo" 5 6.5)), (newL(newC "c" (new_P 4 7)) (newC "d" (new_P 1 1)) (newQ "hierro" 2 1.5)), (newL (newC "e" (new_P 5 7200)) (newC "f" (new_P 35 1)) (newQ "hierro" 1 7)), (newL (newC "g" (new_P 8 33)) (newC "h" (new_P 5 32)) (newQ "plata" 2 3.1412))]
