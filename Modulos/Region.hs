module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where
      
import City 
import Quality
import Link 
import Tunel 

data Region = Reg [City] [Link] [Tunel]
newR :: Region
newR  = Reg [] [] []
foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cs [Link] [Tunel]) ciudad = Reg cs:ciudad Link Tunel
--desiciones que tomar
-- o (Reg cs) ciudad = Reg [ciudad:cs]
linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cs listaL t) ciudad1 ciudad2 calidad = Reg cs [listaL:(newL ciudad1 ciudad2 calidad)] t

estaLink :: City -> City -> [Link] -> Link
estaLink a b [] = []
estaLink a b [link:otrosLinks] | (linksL a b link) == True = link
                               |otherwise estalink a b [otrosLinks]





tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región

--------
connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT ciudad1 ciudad2 (Tun listL) | ((connectsL ciudad1 (head listL)) == True && (connectsL ciudad1 (listL !! 1)) == False && (connectsL ciudad2 (last listL)) == True && (connectsL ciudad2 ( last (init listL)) == False ))
                                      || ((connectsL ciudad2 (head listL)) == True && (connectsL ciudad2 (listL !! 1)) == False && (connectsL ciudad1 (last listL)) == True && (connectsL ciudad1 ( last (init listL)) == False )) = True
                                      | otherwise = False
                                      -- VER CASO QUE FALTA
                                      -- COMO NO PONER LOS TRUE Y FALSE

------

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg listaC listaL []) ciudad1 ciudad2 = False
connectedR (Reg listaC listaL [tun:tuneles]) ciudad1 ciudad2 | connectsT ciudad1 ciudad2 tun = True
                                                             | otherwise connectedR (Reg listaC listaL [tuneles]) ciudad1 ciudad2


linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg listaC [] listaT) ciudad1 ciudad2 = False
linkedR (Reg listaC [lin:links] listaT) ciudad1 ciudad2 | linksL ciudad1 ciudad2 lin = True
                                                        | otherwise linkedR (Reg listaC [links] listaT) ciudad1 ciudad2
-- estas dos no son la misma? cual es la diferencia con linksL
delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades


-- HAY DECICIONES QUE TOMAR: NQUE PASA SI YA HAY ENLACE EN A Y C
--CHEQUEAR QUE EXISTE Y LA CAPACIDAD




-- tunelR :: Region -> [ City ] -> Region

   
elembyelem :: a -> [b]-> [b] 
elembyelem a [] = []
elembyelem (x:xs) =  
                    |elembyelem xs
                    

--tunel = [[AB,BC,CD] , [BC,CD] , [DE,EF]]
--LINK = BC
--3

hayCapacidad :: Link -> [Tunel] -> Bool
hayCapacidad link listaT | ((capacityL link )<= joinlinks listaT link ) = False
                         | otherwise True

joinlinks :: [Tunel] -> Link -> Int
joinlinks [] link = 0
joinlinks [tun:tuneles] link = joinlinkschica tun link + joinlinks [tuneles] link

joinlinkschica :: Tunel -> Link -> Int
joinlinkschica tunel link | usesT link tunel = 1
                          | otherwise = 0