import vk as vk
from flask import Flask, json, request
from tornado.httpserver import HTTPServer
from tornado.ioloop import IOLoop
from tornado.wsgi import WSGIContainer

from constants import API_TOKEN, FIRE_KEY

from pyfcm import FCMNotification

push_service = FCMNotification(api_key=FIRE_KEY)

from datetime import timedelta
from flask import make_response, request, current_app
from functools import update_wrapper


def crossdomain(origin=None, methods=None, headers=None,
                max_age=21600, attach_to_all=True,
                automatic_options=True):
    if methods is not None:
        methods = ', '.join(sorted(x.upper() for x in methods))
    if headers is not None and not isinstance(headers, basestring):
        headers = ', '.join(x.upper() for x in headers)
    if not isinstance(origin, basestring):
        origin = ', '.join(origin)
    if isinstance(max_age, timedelta):
        max_age = max_age.total_seconds()

    def get_methods():
        if methods is not None:
            return methods

        options_resp = current_app.make_default_options_response()
        return options_resp.headers['allow']

    def decorator(f):
        def wrapped_function(*args, **kwargs):
            if automatic_options and request.method == 'OPTIONS':
                resp = current_app.make_default_options_response()
            else:
                resp = make_response(f(*args, **kwargs))
            if not attach_to_all and request.method != 'OPTIONS':
                return resp

            h = resp.headers

            h['Access-Control-Allow-Origin'] = origin
            h['Access-Control-Allow-Methods'] = get_methods()
            h['Access-Control-Max-Age'] = str(max_age)
            if headers is not None:
                h['Access-Control-Allow-Headers'] = headers
            return resp

        f.provide_automatic_options = False
        return update_wrapper(wrapped_function, f)
    return decorator

app = Flask(__name__)
session = vk.Session()
api = vk.API(session, v=5.0)


def send_via_vk(user_id, data):
    name = api.users.get(user_ids=user_id)[0]["first_name"]
    api.messages.send(access_token=API_TOKEN, user_id=str(user_id),
                      message="Привет, " + name + "!" + '\n ' + data["text"])


def send_via_push(user_push, data):
    message_title = data["title"]
    if data["type"] == 1:
        data_message = {
            "type": 1,
            "title": data["title"],
            "description": data["text"],
            "url": data["url"]
        }
    else:
        data_message = {
            "type": 0,
            "title": data["title"],
            "description": data["text"]
        }
    result = push_service.notify_single_device(registration_id=user_push, message_title=message_title,
                                               data_message=data_message)

@crossdomain(origin='*')
@app.route('/', methods=['POST'])
def processing():
    try:
        data = request.json
        for user in data['users']:
            if user["notifyThroughPushes"]:
                send_via_push(user["pushToken"], data)
            else:
                if user["notifyThroughVk"]:
                    send_via_vk(user_id=user["vk"], data=data)
        return 'ok'
    except Exception as err:
        return err.__str__()


if __name__ == '__main__':
    http_server = HTTPServer(WSGIContainer(app))
    http_server.listen(80)
    IOLoop.instance().start()
