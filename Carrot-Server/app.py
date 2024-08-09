import serverless_wsgi

import os
os.environ["JOBLIB_MULTIPROCESSING"] = "0"
os.environ["HOME"] = "/tmp"

from flask import Flask
from flask_jwt_extended import JWTManager
from flask_restful import Api
from config import Config

from resources.user import UserLoginResource, UserRegisterResource
from resources.boto3test import FileUploadResource

from flask import Flask, jsonify, make_response

app = Flask(__name__)

api = Api(app)


# 환경변수 셋팅

app.config.from_object(Config)

#jwt 매니저 초기화
jwt = JWTManager(app)


api.add_resource(UserRegisterResource, '/user/register')
api.add_resource(UserLoginResource,'/user/login')
api.add_resource(FileUploadResource,'/uploadimg')


@app.route("/")
def hello_from_root():
    return jsonify(message='Hello from root!')


@app.route("/hello")
def hello():
    return jsonify(message='Hello from path!')


@app.errorhandler(404)
def resource_not_found(e):
    return make_response(jsonify(error='Not found!'), 404)

def handler(event, context):
    return serverless_wsgi.handle_request(app,event,context)



if __name__ == '__main__' :
    app.run()

