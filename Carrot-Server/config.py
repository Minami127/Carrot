class Config :
    HOST = 'database-1.cfsuebajeebd.ap-northeast-2.rds.amazonaws.com'
    DATABASE = 'Carrot'
    DB_USER = 'minami'
    DB_PASSWORD = '1234'

    PASSWORD_SALT = 'mianmaks11'

    
    S3_BUCKET = 'carrot-server-s3'
    S3_LOCATION = 'https://carrot-server-s3.s3.ap-northeast-2.amazonaws.com/'

    AWS_ACCESS_KEY_ID = 'AKIAXFEZDOCFBLBUHYM4'
    AWS_SECRET_ACCESS_KEY = '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'
    

    ### JWT 관련 변수 셋팅
    JWT_SECRET_KEY = 'Minami1##bye~~'
    JWT_ACCESS_TOKEN_EXPIRES = False
    PROPAGATE_EXCEPTIONS = True