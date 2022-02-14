# Case 1

Write a program that will read a list of boxes sizes from a file and report invalid entries.

The file format is a CSV where each line contains 3 dimensions in order **height**, **width**, **length**.  Each 
dimension is a numerical distance value followed by a unit.  Supported units are "inch", "foot", "mm" (millimeter), 
"cm" (centimeter) and "m" (meter).

Example input
```
3 inch, 2 inch, 6 inch
0.6 m, 30 cm, 50 cm
300 mm, 100 mm, 200 mm
```

# Case 2

Print the boxes to standard output in descending order by volume, one line per box.  Each line should include the dimensions
with the original units and the volume of the box.  The volume may be in a common unit.  For the above example input the
output could be

```
0.6 m, 30 cm, 50 cm, 0.09 m^3
300 mm, 100 mm, 200 mm, 0.006 m^3
3 inch, 2 inch, 6 inch, 0.00059 m^3
```

# Case 3

Print to standard output possible arrangements for placing smaller boxes inside of larger boxes.  It is not required to 
produce an optimal arrangement of boxes.  The only requirement is that there is no outermost box of one nested stack 
which will fit inside of the innermost box of another nested stack.

The output should print one nested stack of boxes per line.  Each line is a list of triples containing the
dimensions (height, width, length) of a box in the original unit read from the input.
"(3 in, 2 in, 6 in), (250 mm, 400 mm, 300 mm)"

# Unit conversions
Use the following unit conversions.
```
1 m = 39.37008 inch
1 m = 3.28084 foot
1 m = 100 cm
1 m = 1000 mm
```
