module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ material capacity delay | capacity /= 0 && delay /= 0 = Qua material capacity delay
                             | otherwise = error "Ni la demora ni la capacidad del link pueden ser 0"
                             
capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua material capacity delay) = capacity

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua material capacity delay) = delay