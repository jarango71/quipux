from django.urls import path
from drf_spectacular.views import SpectacularAPIView, SpectacularSwaggerView

from .views import AdminView, UserView

urlpatterns = [
    path('api/test/admin/', AdminView.as_view(), name='test-admin'),
    path('api/test/user/', UserView.as_view(), name='test-user'),

    path('api/test/mixin/admin/', AdminView.as_view(), name='test-admin'),
    path('api/test/mixin/user/', UserView.as_view(), name='test-user'),

    path("api/schema/", SpectacularAPIView.as_view(), name="schema"),
    path("api/swagger-ui/", SpectacularSwaggerView.as_view(url_name="schema"), name="swagger-ui"),
]
