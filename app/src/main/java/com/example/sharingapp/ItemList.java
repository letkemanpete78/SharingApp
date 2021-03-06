package com.example.sharingapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * ItemList class
 */
public class ItemList {

    private static ArrayList<Item> items;
    private String FILENAME = "items.sav";

    List<Item> fullItemList() {
        return listOfItems(null);
    }

    List<Item> availbleItemList() {
        return listOfItems(true);
    }

    List<Item> borrowedItemList() {
        return listOfItems(false);
    }

    private List<Item> listOfItems(Boolean isAvailble) {
        items = new ArrayList<>();
        return items;
    }

    List<Item> getItems() {
        return items;
    }

    void setItems(ArrayList<Item> item_list) {
        items = item_list;
    }

    void addItem(Item item) {
        items.add(item);
    }

    void deleteItem(Item item) {
        items.remove(item);
    }

    Item getItem(int index) {
        return items.get(index);
    }

    int getIndex(Item item) {
        int pos = 0;
        for (Item i : items) {
            if (item.getId().equals(i.getId())) {
                return pos;
            }
            pos = pos + 1;
        }
        return -1;
    }

    int getSize() {
        return items.size();
    }

    void loadItems(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
            items = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            items = new ArrayList<>();
        } catch (IOException e) {
            items = new ArrayList<>();
        }
    }

    void saveItems(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(items, osw);
            osw.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Item> filterItemsByStatus(Status status) {
        ArrayList<Item> selected_items = new ArrayList<>();
        for (Item i : items) {
            if (i.getStatus().equals(status)) {
                selected_items.add(i);
            }
        }
        return selected_items;
    }
}

