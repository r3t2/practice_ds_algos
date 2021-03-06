```
The heavy pill: 20 bottles with pills of 1g except 1 bottle with pills of 1.1g. 
Given a scale that provides exact measurement, determine which bottle has the heavy pill.
Only one use of the scale is permitted.
```
**Approach 1:** number the bottles 1-20. Take wi pills from bottle i. wi = 10^(2*i-1). This will call the decimal place corresponding to 2*i have a one. 
For example: three bottles.
take 10^1, 10^3, 10^5 pills from each. 
The weight on the scale will read:
* If none of the pills are heavy  : 101010
* If bottle 1 has heavier pills   : 101011 <- 1 in 10^0 place.
* If bottle 2 has heavier pills   : 101110 <- 1 in 10^2 place
* If bottle 3 has heavier pills   : 111010 <- 1 in 10^4 place

But here, the weights are too large and probably impractical.

**Approach 2:** Take i pills from i^th bottle: wi = i.
The weight on the scale will read:
* If none of the pills are heavy    : 1 + 2 + 3 = 6 = N*(N+1)/2
* If bottle 1 has heavier pills     : 1.1 + 2 +3 = 6.1
* If bottle 2 has heavier pills     : 1 + 2.2 + 3 = 6.2
* If bottle 3 has heavier pills     : 1 + 2 + 3.3 = 6.3

The bottle with heavier pill = (measured_weight - N*(N+1)/2) / 0.1