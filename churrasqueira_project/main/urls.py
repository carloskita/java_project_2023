from django.urls import path
from . import views

urlpatterns = [
    path('', views.login, name='login'),
    path('login', views.login, name='login'),
    path('calendario', views.calendario, name='calendario'),
    path('cadastro', views.cadastro, name='cadastro'),
]
