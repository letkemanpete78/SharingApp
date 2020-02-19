package com.example.sharingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Item class
 */
public class Item {

    protected transient Bitmap image;
    protected String image_base64;
    private String title;
    private String maker;
    private String description;
    private Dimensions dimensions;
    private com.example.sharingapp.Status status;
    private String borrower;
    private String id;

    public Item() {
    }

    public Item(String title, String maker, String description, Dimensions dimensions, Bitmap image, Status status) {
        this.title = title;
        this.maker = maker;
        this.description = description;
        this.dimensions = dimensions;
        this.status = status;
        this.borrower = "";
        addImage(image);

        if (id == null) {
            setId();
        } else {
            updateId(id);
        }
    }

    public String getId() {
        return this.id;
    }

    private void setId(String itemId) {
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public void updateId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public com.example.sharingapp.Status getStatus() {
        return status;
    }

    public void setStatus(com.example.sharingapp.Status status) {
        this.status = status;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public Item findById(String itemId) {
        Item item = new Item();
        item.setId(itemId);
        return item;
    }

    public void addImage(Bitmap new_image, User user) {
        addImage(new_image);
    }

    public Bitmap viewImage(String itemId) {
        return getImage();
    }

    public Boolean deleteImage(String itemId, String fileName) {
        return true;
    }

    public void addImage(Bitmap new_image) {
        if (new_image != null) {
            image = new_image;
            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            new_image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
            byte[] b = byteArrayBitmapStream.toByteArray();
            image_base64 = Base64.encodeToString(b, Base64.DEFAULT);
        }
    }

    public Bitmap getImage() {
        if (image == null && image_base64 != null) {
            byte[] decodeString = Base64.decode(image_base64, Base64.DEFAULT);
            image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        }
        return image;
    }

    public boolean addItem(Item item, String userId) {
        return true;
    }

    public boolean deleteItem(String itemId, String userId) {
        return true;
    }

    public List<Item> getBowweredItems(String userId) {

        List<Item> items = getItems(userId, true);
        return items;
    }

    public boolean updateItem(Item item, String userId) {
        return true;
    }

    public Item getItem(String userId, String itemId) {
        return new Item();
    }

    private List<Item> getItems(String userId, Boolean isBorrowered) {
        List<Item> items = new ArrayList<>();
        return items;
    }

    private List<Item> getItems(String userId) {
        List<Item> items = new ArrayList<>();
        return items;
    }

}

