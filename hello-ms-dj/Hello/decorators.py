from functools import wraps
from rest_framework.response import Response
from rest_framework import status
from Hello.authentication import JWTAuthentication


def PreAuthorize(allowed_roles):
    def decorator(view_func):
        @wraps(view_func)
        def wrapped_view(request, *args, **kwargs):
            roles = JWTAuthentication.roles  # Obtiene los roles directamente desde la clase de autenticación

            if not roles:
                return Response({"detail": "No roles in the token"}, status=status.HTTP_401_UNAUTHORIZED)

            if "error" in roles:
                return Response({"detail": roles["error"]}, status=status.HTTP_401_UNAUTHORIZED)

            if any(role in roles for role in allowed_roles):
                return view_func(request, *args, **kwargs)
            else:
                return Response({"detail": "Unauthorized"}, status=status.HTTP_403_FORBIDDEN)

        return wrapped_view

    return decorator
