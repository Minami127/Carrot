from flask import Flask
from flask_jwt_extended import JWTManager
from flask_restful import Api

app = Flask(__name__)
# 환경변수 셋팅
app.config.from_object(Config)

#jwt 매니저 초기화
jwt - JWTManager(app)

api = Api(app)


if __name__ == '__main__' :
    app.run()