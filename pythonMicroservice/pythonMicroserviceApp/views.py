from django.shortcuts import render
from .models import *
import json
from django.http import HttpResponse
from django.core import serializers
# Create your views here.

def searchByIngredients(searchString):
    result = Recipe.objects.filter(ingredients__name__contains=searchString)
    return result

def searchByName(searchString):
    result = Recipe.objects.filter(name__contains=searchString)
    return result

def searchByGenre(searchString):
    result = Recipe.objects.filter(genre__contains=searchString)
    return result

def searchByMadeBy(searchString):
    result = Recipe.objects.filter(madeBy__contains=searchString)
    return result

def search(request,searchString):
    ingredientSearch=searchByIngredients(searchString)
    nameSearch=searchByName(searchString)
    genreSearch=searchByGenre(searchString)
    madeBy=searchByMadeBy(searchString)
    a=len(ingredientSearch)
    b = len(nameSearch)
    c = len(genreSearch)
    d = len(madeBy)

    returnValue=[]
    for obj in ingredientSearch:
        dictToBeSent = {
            "name": obj.name,
            "genre": obj.genre,
            "madeBy":obj.madeBy,
        }
        returnValue.append(dictToBeSent)
    for obj in nameSearch:
        dictToBeSent = {
            "name": obj.name,
            "genre": obj.genre,
            "madeBy": obj.madeBy,
        }
        returnValue.append(dictToBeSent)
    for obj in genreSearch:
        dictToBeSent = {
            "name": obj.name,
            "genre": obj.genre,
            "madeBy": obj.madeBy,
        }
        returnValue.append(dictToBeSent)
    for obj in madeBy:
        dictToBeSent = {
            "name": obj.name,
            "genre": obj.genre,
            "madeBy": obj.madeBy,
        }
        returnValue.append(dictToBeSent)
    return HttpResponse(json.dumps(returnValue),'application/json')