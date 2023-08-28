module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import City 
import Quality
import Link 
import Tunel 


data Region = Reg [City] [Link] [Tunel]

instance Show Region where
    show (Reg listC listL listT) =
        "Reg " ++ show listC ++ " " ++ show listL ++ " " ++ show listT


newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg listC listL listT) city2 | elem city2 listC = Reg listC listL listT
                                     | otherwise = Reg (listC ++ [city2]) listL listT

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg listC listL listT) city1 city2 quality | elem (newL city1 city2 quality) listL = Reg listC listL listT
                                                  | otherwise = Reg listC (listL ++ [newL city1 city2 quality]) listT

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg listC listL listT) cities  | elem (newT (linksForTunel  cities listL listT)) listT = Reg listC listL listT
                                       | otherwise = Reg listC listL (listT ++ [newT (linksForTunel  cities listL listT)])

linksForTunel  :: [City] -> [Link] -> [Tunel] -> [Link]
linksForTunel  [a , b] listL listT | capacityLeft listT (linkThatConnects  listL a b) = [ linkThatConnects  listL a b]
                                    | otherwise = error "No hay espacio suficiente en alguno de los links"
linksForTunel  (a:b:cities) listL listT | capacityLeft listT (linkThatConnects  listL a b) = [linkThatConnects  listL a b] ++ linksForTunel  (b:cities) listL listT
                                         | otherwise = error "No hay espacio suficiente en alguno de los links"

linkThatConnects  :: [Link] -> City -> City -> Link
linkThatConnects  [link] city1 city2  | linksL city1 city2 link = link
                                      | otherwise = error ("No hay un link hecho entre las ciudades " ++ nameC city1 ++ " y " ++ nameC city2)
linkThatConnects  (lin:links) city1 city2  | linksL city1 city2 lin = lin
                                           | otherwise = linkThatConnects  links city1 city2

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg listC listL []) city1 city2 = False
connectedR (Reg listC listL (tun:tunels)) city1 city2 | connectsT city1 city2 tun = True
                                                      | otherwise = connectedR (Reg listC listL tunels) city1 city2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg listC [] listT) city1 city2 = False
linkedR (Reg listC (lin:links) listT) city1 city2 | linksL city1 city2 lin = True
                                                  | otherwise = linkedR (Reg listC links listT) city1 city2

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg listC listL listT) city1 city2 = delayT (whichTconnects listT city1 city2)

whichTconnects :: [Tunel] -> City -> City -> Tunel
whichTconnects [tun] _ _ = tun
whichTconnects (tun:tunels) city1 city2 | connectsT city1 city2 tun = tun
                                        | otherwise = whichTconnects tunels city1 city2

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg listC listL listT) city1 city2 = (capacityL (linkThatConnects listL city1 city2)) - (tunelsInLink listT (linkThatConnects listL city1 city2))

capacityLeft :: [Tunel]-> Link -> Bool
capacityLeft listT link = ((capacityL link) > (tunelsInLink listT link)) 

tunelsInLink :: [Tunel] -> Link -> Int
tunelsInLink [] link = 0
tunelsInLink (tun:tunels) link | usesT link tun = 1 + tunelsInLink tunels link
                               | otherwise = tunelsInLink tunels link