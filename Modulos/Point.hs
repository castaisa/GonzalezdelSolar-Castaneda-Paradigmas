module Point ( Point, new_P, dif_P)
   where

data Point = Poi Int Int deriving (Eq, Show)

new_P :: Int -> Int -> Point
new_P x y = Poi x y

dif_P :: Point -> Point -> Float  -- distancia absoluta

dif_P (Poi x0 y0) (Poi x1 y1) = sqrt (fromIntegral ((x0 - x1) ^ 2 + (y0 - y1) ^ 2))
