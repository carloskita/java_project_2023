from django.shortcuts import render, redirect
from .forms import RegisterForm, PostForm
from django.contrib.auth.decorators import login_required
from django.contrib.auth import login, logout, authenticate
from .models import Post

# Create your views here.

def home(request):
    return render(request, 'churrasqueira/home.html')

def login(request):
    if request.method == "GET":
        return render(request, 'registration/login.html')
    else:
        username = request.POST.get('username')
        senha = request.POST.get('password')

@login_required(login_url="/login/")
def datas(request):
    posts = Post.objects.all()
    
    return render(request, 'churrasqueira/datas.html', {"posts": posts})

@login_required(login_url="/login/")
def calendario(request):
    if request.method == "POST":
        form = PostForm(request.POST)
        if form.is_valid():
            post = form.save(commit=False)
            post.author = request.user
            post.save()
            return redirect("/calendario")
    else:
        form = PostForm()
        
    return render(request, 'churrasqueira/calendario.html', {"form": form})

@login_required(login_url="/login/")
def cadastro(request):
    if request.method =='POST':
        form = RegisterForm(request.POST)
        if form.is_valid():
            user = form.save()
            return redirect('/calendario')
    else:
        form = RegisterForm()
    
    return render(request, 'registration/cadastro.html', {"form": form})