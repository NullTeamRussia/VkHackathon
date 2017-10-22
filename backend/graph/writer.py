def write(json, file):
    file = open(file + "input.txt", 'w')
    content = json
    n = content["number"]
    file.write(str(n) + '\n')
    s = ""
    for i in content["nodes"]:
        s += str(i["weight"]) + " "
    s.strip()
    file.write(s+'\n')
    matrix = []
    for i in range(n):
        matrix.append([0 for i in range(n)])
    for i in content["edges"]:
        matrix[i["from"]][i["to"]] = i["weight"]
        matrix[i["to"]][i["from"]] = i["weight"]
    for i in range(n):
        s = ""
        for j in range(n):
            s += str(matrix[i][j]) + " "
        s.strip()
        file.write(s+'\n')
    file.write(str(json["start"]) + " " + str(json["end"]) + '\n')
    file.close()
