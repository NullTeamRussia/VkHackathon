import os

import time
from flask import Flask, request, jsonify
from tornado.wsgi import WSGIContainer
from tornado.httpserver import HTTPServer
from tornado.ioloop import IOLoop
import subprocess
import reader
import writer


def bash_command(cmd):
    subprocess.Popen(['/bin/bash', '-c', cmd])


debug = False
if debug:
    PATH = "/home/daniil/PycharmProjects/vk/graph/"
else:
    PATH = "/root/graph-web/"
    BIN_PATH = ""
app = Flask(__name__)


def build_shortest_json(interesting_json, start, end):
    return {
        "start": start,
        "end": end,
        "nodes": interesting_json["nodes"],
        "number": interesting_json["number"],
        "edges": interesting_json["edges"]
    }


@app.route('/interesting', methods=["POST"])
def interesting():
    try:
        json = request.json
        entry = json["entry"]
        weight = 0
        path = []
        for i in json["interesting"]:
            writer.write(build_shortest_json(json, entry, i), PATH)
            os.system("rm " + PATH + "output.txt")
            cmd = subprocess.Popen(PATH + "main").communicate()[0]

            time.sleep(0.5)
            data = reader.read(PATH)
            weight += data["weight"]
            path = path[:-1]
            path.extend(data["path"].copy())
            entry = path[-1]
        # find closest exit
        min_weight = 100000
        min_path = []
        for i in json["exits"]:
            writer.write(build_shortest_json(json, entry, i), PATH)
            os.system(BIN_PATH)
            data = reader.read(PATH)
            temp_weight = data["weight"]
            if temp_weight < min_weight:
                min_weight = temp_weight
                min_path = data["path"].copy()
        path = path[:-1]
        path.extend(min_path)
        weight += min_weight
        return jsonify({
            "result": {
                "path": path,
                "weight": weight
            },
            "error": ""
        })
    except Exception as err:
        return jsonify({
            "result": {},
            "error": err.__str__()
        })


@app.route('/shortest', methods=["POST"])
def shortest():
    try:
        json = request.json
        writer.write(json, PATH)
        from subprocess import call
        subprocess.call((PATH + "call.sh"), shell=True)
        return jsonify({
            "result": reader.read(PATH),
            "error": ""
        })
    except Exception as err:
        return jsonify({
            "result": {},
            "error": err.__str__()
        })


if __name__ == '__main__':
    try:
        os.environ.pop('PYTHONIOENCODING')
    except KeyError:
        pass
    http_server = HTTPServer(WSGIContainer(app))
    http_server.listen(8000)
    IOLoop.instance().start()
