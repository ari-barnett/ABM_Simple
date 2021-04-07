# ABM

This agent-based model is represented using a 2D numeric array with confined board space of 150 X 150, with the randomization of agent placement (~5% placement).  
For this model the general rules for advancement are: 

- Neighbourhood Style: Moore

1.	At the end of each step (time = 1 day) each cell with the current state alive has a 50% chance of survival to the next day. 
2.	Cells that survive will then have the instance to attempt reproduction depending on available spacing within their surrounding neighborhood - an agent with an available open lattice can reproduce. 
3.	For this model, newly formed cells of the reproduction method are prohibited from attempting to reproduce themselves as they have not passed the appropriate survival method. 

 Ari Barnett (Roldan) St. Petersburg College 2021
