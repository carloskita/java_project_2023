from django.shortcuts import render, redirect
from .forms import RegisterForm
from django.contrib.auth import login, logout, authenticate

# Create your views here.
def login(request):
    if request.method == "GET":
        return render(request, 'registration/login.html')
    else:
        username = request.POST.get('username')
        senha = request.POST.get('password')

def calendario(request):
    return render(request, 'churrasqueira/calendario.html')

def cadastro(request):
    if request.method =='POST':
        form = RegisterForm(request.POST)
        if form.is_valid():
            user = form.save()
            login(request, user)
            return redirect('/calendario')
    else:
        form = RegisterForm()
    
    return render(request, 'registration/cadastro.html', {"form": form})