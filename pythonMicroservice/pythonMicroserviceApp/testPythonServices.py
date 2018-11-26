from django.test import TestCase
from pythonMicroserviceApp.models import Ingredient,Recipe

class ObjectCreationTestCase(TestCase):
    def setUp(self):
        ing1=Ingredient.objects.create(name="Masala")
        ing2=Ingredient.objects.create(name="Egg")
        recipe1=Recipe.objects.create(name="Egg Masala",madeBy="pswargam",description="This is the first description")
        recipe1.save()
        recipe1.ingredients.add(ing1)
        recipe1.ingredients.add(ing2)
    def test_dbCreatesIngredients(self):
        ing1 = Ingredient.objects.get(name="Masala")
        self.assertEqual(ing1.name,"Masala")

    def test_dbCreatesRecipes(self):
        recipe1= Recipe.objects.get(name="Egg Masala")
        self.assertEqual(recipe1.name,"Egg Masala")

    def test_searchMethod(self):
        response = self.client.get("/recipes/pythonSearch/a,b/2/")
        self.assertEqual(response.status_code,200)
