
module City ( City, newC, nameC, distanceC )
   where

import Point 

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC nom punto = Cit nom punto

nameC :: City -> String
nameC (Cit nom punto) = nom

distanceC :: City -> City -> Float
distanceC (Cit nom punto ) (Cit nom2 punto2 ) = dif_P punto punto2 --hacer lo mismo con los otros