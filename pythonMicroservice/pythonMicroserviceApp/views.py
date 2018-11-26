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
def searchByMinimumTime(minimumTime):
    result = Recipe.objects.filter(timeTaken__gte=minimumTime)
    return result

def searchByMaximumTime(maximumTime):
    result = Recipe.objects.filter(timeTaken__lte=maximumTime)
    return result

def search(request,searchString,timeString):

    minimumTime=-10
    maximumTime=100000
    if timeString=='1':
        minimumTime=1
        maximumTime=15
    elif timeString=='2':
        minimumTime=16
        maximumTime=45
    else:
        minimumTime=45
    ingredientSearch=[]
    nameSearch=[]
    genreSearch=[]
    madeBy=[]
    minimumTimeSearch = searchByMinimumTime(minimumTime)
    maximumtimeSearch = searchByMaximumTime(maximumTime)
    returnValue = []
    splitString= searchString.split(',')

    if len(splitString)>1:
        for splitValue in splitString:
            ingredientSearch = searchByIngredients(searchString)
            for obj in ingredientSearch:
                ingList = []
                for x in obj.ingredients.all():
                    ingList.append(x.name)
                dictToBeSent = {
                    "name": obj.name,
                    "genre": obj.genre,
                    "madeBy": obj.madeBy,
                    "ingredients": ingList
                }
                if dictToBeSent not in returnValue:
                    returnValue.append(dictToBeSent)
    else:
        nameSearch=searchByName(searchString)
        genreSearch=searchByGenre(searchString)
        madeBy=searchByMadeBy(searchString)
        for obj in nameSearch:
            print(obj.ingredients.all())
            ingList = []
            for x in obj.ingredients.all():
                ingList.append(x.name)
            dictToBeSent = {
                "name": obj.name,
                "genre": obj.genre,
                "madeBy": obj.madeBy,
                "ingredients": ingList
            }
            if dictToBeSent not in returnValue:
                returnValue.append(dictToBeSent)

        for obj in genreSearch:
            print(obj.ingredients.all())
            ingList = []
            for x in obj.ingredients.all():
                ingList.append(x.name)
            dictToBeSent = {
                "name": obj.name,
                "genre": obj.genre,
                "madeBy": obj.madeBy,
                "ingredients": ingList
            }
            if dictToBeSent not in returnValue:
                returnValue.append(dictToBeSent)
        for obj in madeBy:
            ingList = []
            for x in obj.ingredients.all():
                ingList.append(x.name)
            dictToBeSent = {
                "name": obj.name,
                "genre": obj.genre,
                "madeBy": obj.madeBy,
                "ingredients": ingList
            }
            if dictToBeSent in returnValue:
                returnValue.append(dictToBeSent)

    a=len(ingredientSearch)
    b = len(nameSearch)
    c = len(genreSearch)
    d = len(madeBy)


    for obj in minimumTimeSearch:
        ingList = []
        for x in obj.ingredients.all():
            ingList.append(x.name)
        dictToBeSent = {
            "name": obj.name,
            "genre": obj.genre,
            "madeBy": obj.madeBy,
            "ingredients": ingList
        }
        if dictToBeSent not in returnValue:
            returnValue.append(dictToBeSent)
    for obj in maximumtimeSearch:
        ingList = []
        for x in obj.ingredients.all():
            ingList.append(x.name)
        dictToBeSent = {
            "name": obj.name,
            "genre": obj.genre,
            "madeBy": obj.madeBy,
            "ingredients": ingList
        }
        if dictToBeSent not in returnValue:
            returnValue.append(dictToBeSent)
    return HttpResponse(json.dumps(returnValue),'application/json')