Telco

Es una compañia que se dedica a comunicar las ciudades que se susbcriben a su servicio.
Primero las ingresa al mapa de la región. 
-- Hay que hacer un diccionario a algo del estilo? PREGUNTAR
Luego establece vínculos entre ellas de cierta calidad y capacidad.
--ESO ES UN ATRIBUTO DE LA CIUDAD? SE ALMACENA EN ALGUNA VARIABLE?
Finalmente establece canales que conectan distintas ciudades ocupando una unidad de 
capacidad por cada enlace recorrido.
--DEFINIR AL TUNEL CON UN LINK Y A LA CIUDAD CON UN LINK TAMBIEN?

Para sostener este modelo se cuenta con las siguientes entidades:

module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

new_P :: Int -> Int -> Point


differnce_P :: Point -> Point -> Float  -- distancia absoluta
-----------------
module City ( City, newC, nameC, distanceC )
   where

data City = Cit String Point deriving (Eq, Show)

new_C :: String -> Point -> City
name_C :: City -> String --PREGUNTAR
distanceC :: City -> City -> Float
-----------------
module Quality ( Quality, newQ, capacityQ, delayQ ) 
   where

data Quality = Qua String Int Float deriving (Eq, Show)

-- QUALITY:
-- INT son los TUNELES
-- FLOAT es la demora por unidad de distancia que sucede en las conexiones de este canal
-- STRING que es?

newQ :: String -> Int -> Float -> Quality --PREGUNTAR COMO FUNCIONA    : QUALITY LINKLEADA A LA CANT DE TUNELES 
capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión -- 
delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
-------------------
module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
capacityL :: Link -> Int
delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
-------------------
-- PREGUNTAR DIFERENCIA ENTRE TUNEL Y LINK
module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

data Tunel = Tun [Link] deriving (Eq, Show)
-- PREGUNTAR XQ ESTA ENTRE CORCHETES?

newT :: [Link] -> Tunel
connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
-------------------
module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where

data Region = Reg [City] [Link] [Tunel]
newR :: Region
foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades

-- PREGUNTAR QUE ES LA REGION Y PORQUE TE DEVUELVEN REGIIN LAS PRIMERAS COSAS.
