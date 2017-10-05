"""
Purpose of the Python script = Transpose rows and columns of a CSV file (or a matrix delimited by ',') :

pi, po, pu    ==>   pi, bi, di
bi, bo, bu    ==>   po, bo, do
di, do, du    ==>   pu, bu, du

"""

# Data = 3 CSV lines
cvs_input = [["pi", "po", "pu"], ["bi", "bo", "bu"], ["di", "do", "du"]]


# FIRST MAPPER =
# It create the following key/value: (RowID, RowValue), as below =
# [[0, ['pi', 'po', 'pu']], [1, ['bi', 'bo', 'bu']], [2, ['di', 'do', 'du']]]

def map1(inputList):
    mainList = []
    for i,nm in enumerate(inputList):
        mainList.append([i, nm])
    return mainList


# SECOND MAPPER =
# It form a list of each value having the same columnID: (columnId, ColumnValue) and do it for each row => (RowID, List(Values)) =
# The mapper will do not display the rowID, only the values as below :
# [['pi', 'bi', 'di'], ['po', 'bo', 'do'], ['pu', 'bu', 'du']]


def map2(inputList):
    mainList = []
    for j,np in enumerate(inputList):
        innerList = []
        for i,nm in enumerate(inputList):
            innerList.append(inputList[i][1][j])
        mainList.append((innerList))
    return mainList


# Execution of the two map functions and return the transposed list
outMap1 = map1(cvs_input)
outMap2 = map2(outMap1)
outMap2