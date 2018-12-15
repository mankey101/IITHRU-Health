package com.iotaconcepts.iithru;

public class Product
{
    String name;
    int price;
    int image;
    boolean box;

    Product(int _price, String name)
    {
        this.name= name;
        price = _price;
    }
}