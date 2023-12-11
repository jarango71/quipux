from rest_framework.response import Response
from rest_framework import status


class AdminAuthorizationMixin:
    @staticmethod
    def check_admin_authorization(self, request):
        roles = request.roles

        if not roles:
            return Response({"detail": "No roles in the token"}, status=status.HTTP_401_UNAUTHORIZED)

        if "error" in roles:
            return Response({"detail": roles["error"]}, status=status.HTTP_401_UNAUTHORIZED)

        if "ADMIN_CLIENT" in roles:
            return True
        else:
            return False


class UserAuthorizationMixin:
    @staticmethod
    def check_user_authorization(self, request):
        roles = request.roles

        if not roles:
            return Response({"detail": "No roles in the token"}, status=status.HTTP_401_UNAUTHORIZED)

        if "error" in roles:
            return Response({"detail": roles["error"]}, status=status.HTTP_401_UNAUTHORIZED)

        if "USER_CLIENT" in roles:
            return True
        else:
            return False
