from flask import request
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful import Resource
from config import Config
from mysql_connection import get_connection
from mysql.connector import Error
import boto3
from datetime import datetime


class PostListResource(Resource):

    
    @jwt_required()
    def get(self) :
        id = get_jwt_identity()
        offset = request.args.get('offset')
        limit = request.args.get('limit')

        try :
            connection = get_connection()

            query = '''select p.id,seller_id,category_id,title,price,description,product_state,viewCnt,i.product_image_url,p.created_at,p.updated_at,p.location
                        from products p
                        left join product_image i ON p.id = i.product_id
                        left join users u on p.seller_id = u.id
                        where p.seller_id != %s 
                        limit ''' + offset + ''' , ''' + limit + ''';
                        '''
            
            record = (id, )
            cursor = connection.cursor(dictionary=True)
            cursor.execute(query,record)

            result_list = cursor.fetchall()

            cursor.close()
            connection.close()

        except Error as e:
            print(Error)
            cursor.close()
            connection.close()
            return{"error" : str(e)},500 
        
        # 날짜 포맷 변경 
        i = 0
        i = 0
        for i, row in enumerate(result_list):
            result_list[i]['created_at'] = row['created_at'].isoformat()
            result_list[i]['updated_at'] = row['updated_at'].isoformat()
        return {"result" : "success", "items" : result_list, "count" : len(result_list)}, 200
    
class PostDetailResource(Resource) :

    @jwt_required()
    def get(self, id):


        try :
            connection = get_connection()

            query = '''select p.id,seller_id,u.nickname,u.profile_img,category_id,title,price,description,product_state,viewCnt,i.product_image_url,p.created_at,p.updated_at,p.location
                        from products p
                        left join product_image i ON p.id = i.product_id
                        left join users u ON p.seller_id = u.id
                        where p.id = %s;
                        '''
            
            record = (id, )
            cursor = connection.cursor(dictionary=True)
            cursor.execute(query,record)

            result_list = cursor.fetchall()

            cursor.close()
            connection.close()

        except Error as e:
            print(Error)
            cursor.close()
            connection.close()
            return{"error" : str(e)},500 
        
        # 날짜 포맷 변경 
        i = 0
        for i, row in enumerate(result_list):
            result_list[i]['created_at'] = row['created_at'].isoformat()
            result_list[i]['updated_at'] = row['updated_at'].isoformat()
        return {"result" : "success", "items" : result_list, "count" : len(result_list)}, 200
    

class PostAddResource(Resource) :

    # 포스팅
    @jwt_required()
    def post(self) :


        try :
            connection = get_connection()

            query = '''insert into products (seller_id, category_id, title, price, description, location, product_state) 
                    values (%s,%s,%s,%s,%s,%s,%s);'''
            
            record = (id, )
            cursor = connection.cursor(dictionary=True)
            cursor.execute(query,record)


            cursor.close()
            connection.close()


        except Error as e:
            print(Error)
            cursor.close()
            connection.close()
            return{"error" : str(e)},500 
        
# 게시글 삭제
#class DelectPostResource(Resource) :
#
#    @jwt_required()
#    def delect(self,products_id) :

        




            









