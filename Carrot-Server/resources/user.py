from flask import request
from flask_jwt_extended import create_access_token, get_jwt, get_jwt_identity, jwt_required
from flask_restful import Resource
from mysql_connection import get_connection
from mysql.connector import Error

from email_validator import EmailNotValidError, validate_email

from utils import check_password, hash_password

class UserRegisterResource(Resource) :

    def post(self) :

        data = request.get_json()

        try : 
            validate_email(data['email'])
        except EmailNotValidError as e :
            print(e)
            return{'error' : str(e)}, 400
        
        if len(data['password']) < 4 or len(data['password']) > 12 :
            return {'error' : '비밀번호 길이를 확인하세요'}, 400
        
        password = (data['password'])

        print(password)


        try :
            connection = get_connection()
            
            # 이메일 중복 검사 쿼리
            check_query = '''SELECT * FROM users WHERE email = %s'''
            cursor = connection.cursor(dictionary=True)
            cursor.execute(check_query, (data['email'],))
            result = cursor.fetchone()

            if result:
                cursor.close()
                connection.close()
                return {'error': '이미 존재하는 이메일입니다.'}, 400
            
            # 이메일 중복 검사 쿼리
            check_query = '''SELECT * FROM users WHERE nickname = %s'''
            cursor = connection.cursor(dictionary=True)
            cursor.execute(check_query, (data['nickname'],))
            result = cursor.fetchone()

            if result:
                cursor.close()
                connection.close()
                return {'error': '이미 존재하는 닉네임입니다.'}, 400

            query = '''insert into users
                        (email,password,nickname,location)
                        values
                        (%s,%s,%s,%s);'''
            
            record = (data['email'],
                      password,
                      data['nickname'],
                      data['location'])
            
            cursor = connection.cursor()
            cursor.execute(query,record)
            connection.commit()

            user_id = cursor.lastrowid

            cursor.close()
            connection.close()

        except Error as e :
            print(e)
            cursor.close()
            connection.close()
            return{"error" : str(e)}, 500
        
        access_token = create_access_token(user_id)

        return{'result' : 'success',
               'accessToken' : access_token},200
    

class UserLoginResource(Resource) :
     
    def post(self) :
         
        data = request.get_json()

        try :
             
            connection = get_connection()

            query = '''select * from users
                        where email = %s;'''
             
            record = (data['email'] , )

            cursor = connection.cursor(dictionary=True)
            cursor.execute(query,record)

            result_list = cursor.fetchall()
            print(result_list)

            cursor.close()
            connection.close()

        except Error as e :
            print(e)
            cursor.close()
            connection.close()
            return{"error" : str(e)}, 500
        
        # 회원 가입을 안한경우, 리스트에 데이터가 없다.
        
        if len(result_list) == 0 :
            return {"error" : "없는 회원 입니다."}, 400
        
        # 회원 ID 정보가 일치하였으니, 비밀번호를 체크한다.
        # 로그인한 사람이 마지막에 입력한 비밀번호 data['password']
        # 회원가입할때 입력했던, 암호화된 비밀번호 DB에있음
        # result_list에 들어있고
        # 리스트의 첫번째 데이터에 들어있다

        check = data['password'], result_list[0]['password']

        if check == False :
            return {"error" : "비밀번호가 틀립니다."}, 400
        
        access_token = create_access_token(result_list[0]['id'])

        return {"result" : "success"
                 , "accessToken" : access_token}

        



             

         


            




