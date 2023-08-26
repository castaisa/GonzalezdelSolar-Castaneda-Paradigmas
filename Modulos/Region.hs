module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where
import City 
import Quality
import Link 
import Tunel 
import Control.Exception

data Region = Reg [City] [Link] [Tunel]
newR :: Region
newR  = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg listaC listaL listaT) ciudad2 = Reg (listaC ++ [ciudad2]) listaL listaT

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg listaC listaL listaT) ciudad1 ciudad2 calidad = Reg listaC (listaL ++ [newL ciudad1 ciudad2 calidad]) listaT

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg listaC listaL listaT) ciudades = Reg listaC listaL (listaT ++ [newT (juntalinks ciudades listaL listaT)])

juntalinks :: [City] -> [Link] -> [Tunel] -> [Link]
juntalinks [a , b] listaL listaT = [buscaLink listaL a b] -- cheq q haya cap 
juntalinks (a:b:ciudades) listaL listaT = 
                                 if hayEspacio listaT (buscaLink listaL a b) 
                                 then [ buscaLink listaL a b] ++ juntalinks (b:ciudades) listaL listaT
                                 else throw (ErrorCall "No hay espacio suficiente en alguno de los links")

buscaLink :: [Link] -> City -> City -> Link
buscaLink [link] ciu1 ciu2  | linksL ciu1 ciu2 link = link
                            | otherwise = throw (ErrorCall $ "No hay un link hecho entre las ciudades " ++ nameC ciu1 ++ " y " ++ nameC ciu2)
buscaLink (lin:links) ciu1 ciu2  | linksL ciu1 ciu2 lin = lin
                                 | otherwise = buscaLink links ciu1 ciu2

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg listaC listaL []) ciudad1 ciudad2 = False
connectedR (Reg listaC listaL (tun:tuneles)) ciudad1 ciudad2 | connectsT ciudad1 ciudad2 tun = True
                                                             | otherwise = connectedR (Reg listaC listaL tuneles) ciudad1 ciudad2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg listaC [] listaT) ciudad1 ciudad2 = False
linkedR (Reg listaC (lin:links) listaT) ciudad1 ciudad2 | linksL ciudad1 ciudad2 lin = True
                                                        | otherwise = linkedR (Reg listaC links listaT) ciudad1 ciudad2

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg listaC listaL listaT) ciudad1 ciudad2 = delayT (cualconecta listaT ciudad1 ciudad2)

cualconecta :: [Tunel] -> City -> City -> Tunel
cualconecta [tun] _ _ = tun
cualconecta (tun:tuneles) ciudad1 ciudad2 | connectsT ciudad1 ciudad2 tun = tun
                                          | otherwise = cualconecta tuneles ciudad1 ciudad2

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg listaC listaL listaT) ciudad1 ciudad2 = (capacityL (dameElLink listaL ciudad1 ciudad2)) - (joinlinks listaT (dameElLink listaL ciudad1 ciudad2))

dameElLink :: [Link] -> City -> City -> Link
dameElLink [link] _ _ = link
dameElLink (lin:links) ciudad1 ciudad2 | linksL ciudad1 ciudad2 lin = lin
                                       | otherwise = dameElLink links ciudad1 ciudad2

hayEspacio :: [Tunel]-> Link -> Bool
hayEspacio listaT link = ((capacityL link) > (joinlinks listaT link)) 

joinlinks :: [Tunel] -> Link -> Int
joinlinks [] link = 0
joinlinks (tun:tuneles) link = joinlinkschica tun link + joinlinks tuneles link

joinlinkschica :: Tunel -> Link -> Int
joinlinkschica tunel link | usesT link tunel = 1
                          | otherwise = 0
                          