package com.user.homedeco.model;

import javafx.scene.control.Button;

import java.util.Objects;

public class Shop {
    private String name;
    private String quantity;
    private String color;
    private String material;
    private Button addToCart;

    public Shop()
    {

    }
    public Shop(String name, String quantity, String color, String material) {
        this.name = name;
        this.quantity = quantity;
        this.color = color;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }


    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(name, shop.name) && Objects.equals(quantity, shop.quantity) && Objects.equals(color, shop.color) && Objects.equals(material, shop.material) && Objects.equals(addToCart, shop.addToCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, color, material, addToCart);
    }
}
