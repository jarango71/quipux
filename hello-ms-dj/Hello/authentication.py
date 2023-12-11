import jwt
from rest_framework import authentication
from rest_framework import exceptions


class JWTAuthentication(authentication.BaseAuthentication):
    roles = []  # Variable de clase para almacenar los roles

    def authenticate(self, request):
        authorization_header = request.META.get('HTTP_AUTHORIZATION')

        if not authorization_header or not authorization_header.startswith('Bearer '):
            return None

        token = authorization_header[7:]

        try:
            payload = jwt.decode(token, verify=False)

            roles = payload.get('resource_access', {}).get('quipux-gateway', {}).get('roles', [])

            self.__class__.roles = roles  # Almacena los roles en la variable de clase

            return (None, roles)

        except jwt.ExpiredSignatureError:
            raise exceptions.AuthenticationFailed('Token has expired')

        except jwt.DecodeError:
            raise exceptions.AuthenticationFailed('Invalid token')
