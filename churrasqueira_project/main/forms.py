from django import forms
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User
from django.contrib.auth import password_validation

class RegisterForm(UserCreationForm):
    name = forms.CharField(max_length=100, label='Nome')
    email = forms.CharField(widget=forms.EmailInput)
    apartmentBlock = forms.CharField(max_length=100, label='Bloco do Apartamento')
    apartmentNum = forms.CharField(max_length=100, label='Numero do Apartamento')
    telephoneNum = forms.CharField(max_length=100, label='Telefone')
    
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.fields['password1'].label = 'Senha'
        self.fields['password2'].label = 'Confirme a senha'
        self.fields['username'].label = 'CPF'

        self.fields['password1'].help_text = 'Crie uma senha de pelo menos 8 letras e/ou numeros'
        self.fields['password2'].help_text = 'Coloque a mesma senha para confirmar'
        self.fields['username'].help_text = 'Digite o CPF corretamente'
    
    
    class Meta:
        model = User
        fields = ["name", "email", "telephoneNum", "apartmentNum", "apartmentBlock", "username", "password1", "password2"]