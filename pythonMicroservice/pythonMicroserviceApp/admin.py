from django.contrib import admin
from .models import Recipe,Ingredient

admin.site.register(Ingredient)
admin.site.register(Recipe)