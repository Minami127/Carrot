from flask import Flask
from flask_jwt_extended import JWTManager
from flask_restful import Api
from config import Config

from resources.user import UserLoginResource, UserRegisterResource

app = Flask(__name__)

api = Api(app)

# 환경변수 셋팅

app.config.from_object(Config)

#jwt 매니저 초기화
jwt = JWTManager(app)


api.add_resource(UserRegisterResource, '/user/register')
api.add_resource(UserLoginResource,'/user/login')


if __name__ == '__main__' :
    app.run()