# Generated by Django 2.0.5 on 2018-09-13 02:43

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Ingredient',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=50)),
            ],
        ),
        migrations.CreateModel(
            name='Recipe',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=50)),
                ('description', models.CharField(max_length=50)),
                ('madeBy', models.CharField(max_length=50)),
                ('genre', models.CharField(max_length=50)),
                ('ingredients', models.ManyToManyField(to='pythonMicroserviceApp.Ingredient')),
            ],
        ),
    ]
