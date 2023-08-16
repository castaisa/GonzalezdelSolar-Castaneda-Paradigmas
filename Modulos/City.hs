import Point
module City ( City, newC, nameC, distanceC )
   where

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC nom Poi = City

nameC :: City -> String
distanceC :: City -> City -> Float