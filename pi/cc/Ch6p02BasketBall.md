```
p -> probability of scoring a basket.
Game 1: shoot only once. You win if you make the basket.
Game 2: shoot 3 times. need to make the hoop at least 2 times to win.
For what values of p should one choose Game 1 over 2.
```
```
Probability of winning in Game 1 = p.
Probability of winning in Game 2 = 3C2*p^2*(1-p) + p^3. ^ --> pow not xor.
                                 = p^2(3 - 2*p)
```

**Intuition:** As p becomes smaller, p^2 becomes smaller faster. For small probability of p, choose Game 1.
Otherwise game 2.


So, 
```
p > 3p^2 - 2^p3 => p(2p^2 -3p + 1) > 0
                    => p(1-p)(1-2p) > 0
                    => p > 1/2 (non trivial case. assuming p>0)
```