from matplotlib import pyplot, colors
import numpy as np
import ABM_tools 
import statistics

lx = []
ly = []
lz = []
i = 0 


print('Inital Population: ', np.sum(ABM_tools.x))

""" EXECUTION OF ABM SYSTEM """
''' ==============================================================  '''
while i < 365: 
    ABM_tools.survival(0.50)
    ABM_tools.reproduce()
    print('Day',i ,'Cells Reproduced: ',np.count_nonzero(ABM_tools.new == 2))
    lz.append(np.count_nonzero(ABM_tools.new == 2))
    final = np.where(ABM_tools.new == 2, 1, ABM_tools.new )
    lx.append(i)
    ly.append(np.sum(final))
    

    print('Total Population: ', np.sum(final))
    ABM_tools.x = np.copy(final)
    i += 1


""" VISUALIZATION """
''' ==============================================================  '''
pyplot.suptitle('Activated Cell Population x Time - 365 Days -- Survival Rate: 0.50')
pyplot.xlabel('Time (Days)')
pyplot.ylabel('Activated Cell Poplulation')
q = np.array(lx)
w = np.array(ly)
m, b = np.polyfit(q, w, 1)
pyplot.plot(q, m*q + b, color='#949494', linestyle='dashed')
pyplot.plot(lx, ly, color='#5866d1')

print('')
print('END OF SIMULATION')
print('=============================================')
print('Standard Deviation of Cells Born: ', statistics.stdev(lz))
print('Average Cell Birth: ', statistics.mean(lz))
print('=============================================')

pyplot.show()
