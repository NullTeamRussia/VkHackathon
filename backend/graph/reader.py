def read(file):
    file = open(file + "output.txt", 'r')
    res = {"weight": int(file.readline().strip()), "path": list(map(int, file.readline().split()))}
    file.close()
    return res
