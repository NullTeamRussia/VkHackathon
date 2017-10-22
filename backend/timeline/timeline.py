from flask import Flask, jsonify, request
from tornado.httpserver import HTTPServer
from tornado.ioloop import IOLoop
from tornado.wsgi import WSGIContainer
import redis

r = redis.StrictRedis()

app = Flask(__name__)


@app.route('/get')
def get():
    try:
        node = int(request.args.get('node'))
        time = int(request.args.get('time'))
        res = r.get(node)[time]
        return jsonify({
            "result": res,
            "error": ""
        })
    except Exception as err:
        return jsonify({
            "result": "",
            "error": err.__str__()
        })


@app.route('/add', methods=["POST"])
def add():
    try:
        json = request.json
        if r.exists(json["node"]):
            data = r.get(json["node"])
            data["time"] = json["value"]
            r.set(json["node"], data)
        else:
            data = {"time": json["value"]}
            r.set(json["node"], data)
        return jsonify({
            "result": "ok",
            "error": ""
        })
    except Exception as err:
        return jsonify({
            "result": "",
            "error": err.__str__()
        })


@app.route('/update', methods=["POST"])
def update():
    try:
        json = request.json
        if r.exists(json["node"]):
            if r.get(json["node"])[json["time"]] is not None:
                data = r.get(json["node"])
                data["time"] = json["value"]
                r.set(json["node"], data)
        return jsonify({
            "result": "ok",
            "error": ""
        })
    except Exception as err:
        return jsonify({
            "result": "",
            "error": err.__str__()
        })


if __name__ == '__main__':
    http_server = HTTPServer(WSGIContainer(app))
    http_server.listen(80)
    IOLoop.instance().start()
