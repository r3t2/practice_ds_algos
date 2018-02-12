/*
String s -> [s0, s1, s2, ..., sN]

si can be only lower case a-z

k -> the number of times si occured between [0,i-1] in s.

form another string q s.t,

qi = (si + k) modulo [a-z]

for example:
abc --> abc (every char occurs only once)
xxxxx --> xyzab (first x occurs only once so far, second x occurs twice so k =1, ...)
*/