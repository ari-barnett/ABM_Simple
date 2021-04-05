import numpy as np
import itertools
import random

grid = []

# The segment of code below sets the intial board randomly
for row in range(50):  
    grid.append([])
    for column in range(50):        
        grid[row].append(np.random.binomial(1,0.50))
        x = np.array(grid)
new = np.copy(x)

def neighbours_of(i, j):
    """Positions of neighbours (this method will include bounds that are out of bound but the position of self will be removed)."""
    neighbours = list(itertools.product(range(i-1, i+2), range(j-1, j+2)))
    neighbours.remove((i, j))
    return neighbours

def validate(matrix, r, c):
    """The validation method will allow for the checking proceedure of surrounding open positions - 
    the border surrounding the gameboard is depicted as 5's since this is an arbitray unused value for advancment   """
    def get(r, c):
        if 0 <= r < len(matrix) and 0 <= c < len(matrix[r]):
            return matrix[r][c]
        else:
            return 5
    neighbors_list = [get(r-1, c-1), get(r-1, c), get(r-1, c+1),
                      get(r  , c-1),              get(r  , c+1),
                      get(r+1, c-1), get(r+1, c), get(r+1, c+1)]     
    return neighbors_list


def survival(p):
    ''' Generation of the surving cell's before the reproductive payoff phase '''
    for idx, i in np.ndenumerate(x):   
        chance = np.random.binomial(1,p)
        if i ==1 and chance == 1:   
            new[idx] = 1
        else:
            new[idx] = 0
    return(new)


def reproduce():
    ''' Generation of new cells dependent on the availabilty of open space - and validation of random space in valid index choices '''
    m = 0
    n = 0
    for idx, i in np.ndenumerate(new):  
        if validate(new,m,n).count(0) > 0 and i == 1:
            while True:
                a = random.choice(neighbours_of(m, n))
                if a in lst and new[a] == 0:
                    new[a] = 2
                    break
        n += 1
        if (n % 50) == 0:
            m += 1
            n = n - 50           
    return new

i = 0

def index_manifest():
    ''' Generates all valid index points within the board size wanted - returns entire manifest '''
    lst = []
    for idx, i in np.ndenumerate(x):
        lst.append(idx)
    return lst
lst = index_manifest()
