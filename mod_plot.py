import numpy as np
import matplotlib.pyplot as plt


#Used for plotting various threshold parameters by increasing the threshold limit as it approaches carrying capacity 

save_results_to = '/Users/ajbarnett/Desktop/Dataset/'
data_path = np.genfromtxt('/Users/ajbarnett/Desktop/Dataset/50085.csv',delimiter=',')


threshold = 0


while threshold < 230000:
    for row in data_path:
        n = 0
        while n < 399:
            if row[n] > threshold:
                plt.plot(row[n:399])    
                #plt.savefig(save_results_to + "%s.png" % flag, dpi = 300)
                break
            else:
                n += 1
    plt.title('THRESHOLD USED: ', loc='left')
    plt.title(threshold, loc='right')
    plt.xlabel('Modified Time')
    plt.ylabel('Population Count')
    flag = '50085=%s' % threshold
    plt.savefig(save_results_to + "%s.png" % flag, dpi = 300)
    threshold += 1000
    plt.close()
    #plt.show()
    


