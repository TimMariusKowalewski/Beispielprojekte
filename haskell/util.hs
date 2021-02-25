my_maximum :: [Integer] -> Integer
my_maximum [x] = x
my_maximum (x:xs) 
    | x < my_maximum xs = my_maximum xs
    | otherwise = x

main=do
    print(my_maximum [33,245,3])