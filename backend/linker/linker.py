# coding: utf8
import requests
from flask import Flask, request, jsonify, json
from tornado.httpserver import HTTPServer
from tornado.ioloop import IOLoop
from tornado.wsgi import WSGIContainer

app = Flask(__name__)

API_URL = "http://31.186.100.160/"
GRAPH_URL = "http://31.186.100.202/interesting"





@app.route('/names')
def name():
    try:
        res = requests.get(API_URL + "nodes")
        nodes = res.json()["_embedded"]["nodes"]
        q = []
        for node in nodes:
            if node["name"] != "":
                q.append(node["name"])
        return jsonify({
            "result": q,
            "error": ""
        })
    except Exception as err:
        return jsonify({
            "result": "",
            "error": err.__str__()
        })


@app.route('/calc', methods=["POST"])
def calc():
    try:
        r = request.json
        res = requests.get(API_URL + "nodes")
        nodes = res.json()["_embedded"]["nodes"]
        res = requests.get(API_URL + "edges")
        edges = res.json()["_embedded"]["edges"]
        edges_result = []
        for i in edges:
            fr = None
            end = None
            for node in nodes:
                if i["startX"] == node["x"] and i["startY"] == node["y"]:
                    fr = nodes.index(node)
                if i["endX"] == node["x"] and i["endY"] == node["y"]:
                    end = nodes.index(node)
            if None != fr or end != None:
                edges_result.append({
                    "from": fr,
                    "to": end,
                    "weight": i["weight"]
                })
        nd = []
        for i in range(len(nodes)):
            nd.append({
                "id": i,
                "weight": 1
            })
        inte = []
        for i in r['interesting']:
            for j in range(len(nodes)):
                if nodes[j]["name"] == i:
                    inte.append(j)
        data = {
            "start": inte[0],
            "end": inte[-1],
            "entry": inte[0],
            "exits": [inte[0]],
            "number": len(nd),
            "nodes": nd,
            "edges": edges_result,
            "interesting": inte
        }

        r = requests.post(GRAPH_URL, json=(json.dumps(data)))
        data = r.json()
        res = []
        for i in data["result"]["path"]:
            res.append({"x": nodes[i]["x"], "y": nodes[i]["y"]})
        return jsonify({
            "result": res,
            "error": ""
        })
    except Exception as err:
        return jsonify({
            "result": "",
            "error": err.__str__()
        })


if __name__ == '__main__':
    http_server = HTTPServer(WSGIContainer(app))
    http_server.listen(5500)
    IOLoop.instance().start()
