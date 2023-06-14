from django import forms
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User
from .models import Post

class RegisterForm(UserCreationForm):
    email = forms.CharField(widget=forms.EmailInput, required=True)
    apartmentBlock = forms.CharField(max_length=100, label='Bloco do Apartamento', required=True)
    apartmentNum = forms.CharField(max_length=100, label='Numero do Apartamento', required=True)
    telephoneNum = forms.CharField(max_length=100, label='Telefone', required=True)
    
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.fields['first_name'].label = 'Nome'
        self.fields['last_name'].label = 'Sobrenome'
        self.fields['password1'].label = 'Senha'
        self.fields['password2'].label = 'Confirme a senha'
        self.fields['username'].label = 'CPF'

        self.fields['password1'].help_text = 'Crie uma senha de pelo menos 8 letras e/ou numeros'
        self.fields['password2'].help_text = 'Coloque a mesma senha para confirmar'
        self.fields['username'].help_text = 'Digite o CPF corretamente'
    
    class Meta:
        model = User
        fields = ["first_name", "last_name", "email", "telephoneNum", "apartmentNum", "apartmentBlock", "username", "password1", "password2"]
        
class PostForm(forms.ModelForm):
    class Meta:
        model = Post
        fields = ["day", "month", "year"]