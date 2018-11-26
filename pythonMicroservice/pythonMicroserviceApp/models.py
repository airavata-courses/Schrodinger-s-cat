from django.db import models

# Create your models here.
class Ingredient(models.Model):
    name = models.CharField(max_length=50)
class Recipe(models.Model):
    name = models.CharField(max_length=50)
    description = models.CharField(max_length=50)
    madeBy = models.CharField(max_length=50)
    genre = models.CharField(max_length=50)
    ingredients = models.ManyToManyField(Ingredient)
    timeTaken = models.IntegerField(default=0)

