from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status

from Hello.decorators import PreAuthorize
from Hello.mixins import AdminAuthorizationMixin, UserAuthorizationMixin


class AdminView(APIView):

    @PreAuthorize(["ADMIN_CLIENT"])
    def get(self, request):
        return Response({"Keycloak with ADMIN CLIENT ROLE"})


class UserView(APIView):

    @PreAuthorize(["USER_CLIENT"])
    def get(self, request):
        return Response({"Keycloak with USER CLIENT ROLE"})


class AdminViewMixin(AdminAuthorizationMixin, APIView):
    def get(self, request):
        if self.check_admin_authorization(request):
            return Response({"Keycloak with ADMIN CLIENT ROLE"})
        else:
            return Response({"detail": "Unauthorized"}, status=status.HTTP_403_FORBIDDEN)


class UserViewMixin(UserAuthorizationMixin, APIView):
    def get(self, request):
        if self.check_user_authorization(request):
            return Response({"Keycloak with USER CLIENT ROLE"})
        else:
            return Response({"detail": "Unauthorized"}, status=status.HTTP_403_FORBIDDEN)