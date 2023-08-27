module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import City 
import Quality
import Link 
import Tunel 
import Control.Exception

data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR  = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg listC listL listT) city2 = Reg (listC ++ [city2]) listL listT

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg listC listL listT) city1 city2 quality = Reg listC (listL ++ [newL city1 city2 quality]) listT

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg listC listL listT) cities = Reg listC listL (listT ++ [newT (tunelsUsedforT  cities listL listT)])

tunelsUsedforT  :: [City] -> [Link] -> [Tunel] -> [Link]
tunelsUsedforT  [a , b] listL listT = 
                                 if capacityLeft listT (findlinkforT  listL a b) 
                                 then [ findlinkforT  listL a b]
                                 else throw (ErrorCall "No hay espacio suficiente en alguno de los links")
tunelsUsedforT  (a:b:cities) listL listT = 
                                 if capacityLeft listT (findlinkforT  listL a b) 
                                 then [ findlinkforT  listL a b] ++ tunelsUsedforT  (b:cities) listL listT
                                 else throw (ErrorCall "No hay espacio suficiente en alguno de los links")

findlinkforT  :: [Link] -> City -> City -> Link
findlinkforT  [link] city1 city2  | linksL city1 city2 link = link
                                  | otherwise = throw (ErrorCall $ "No hay un link hecho entre las ciudades " ++ nameC city1 ++ " y " ++ nameC city2)
findlinkforT  (lin:links) city1 city2  | linksL city1 city2 lin = lin
                                       | otherwise = findlinkforT  links city1 city2

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
availableCapacityForR (Reg listC listL listT) city1 city2 = (capacityL (linkThatConnects listL city1 city2)) - (tunelsUsed listT (linkThatConnects listL city1 city2))

linkThatConnects :: [Link] -> City -> City -> Link
linkThatConnects [link] _ _ = link
linkThatConnects (lin:links) city1 city2 | linksL city1 city2 lin = lin
                                         | otherwise = linkThatConnects links city1 city2

capacityLeft :: [Tunel]-> Link -> Bool
capacityLeft listT link = ((capacityL link) > (tunelsUsed listT link)) 

tunelsUsed :: [Tunel] -> Link -> Int
tunelsUsed [] link = 0
tunelsUsed (tun:tunels) link = usesTorNot tun link + tunelsUsed tunels link

usesTorNot :: Tunel -> Link -> Int
usesTorNot tunel link | usesT link tunel = 1
                          | otherwise = 0

