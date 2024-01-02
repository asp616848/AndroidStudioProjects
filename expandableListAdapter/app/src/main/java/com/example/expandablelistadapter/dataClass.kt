package com.example.expandablelistadapter

class dataClass {
    var item = HashMap<String, List<String>>()
    private var fruits = ArrayList<String>()
    init{
    fruits.add("Apple")
    fruits.add("Banana")
    fruits.add("Orange")
    fruits.add("Kiwi")
    fruits.add("Grapes")}
    private var vegetables = ArrayList<String>()
    init {
    vegetables.add("Carrot")
    vegetables.add("Potato")
    vegetables.add("Tomato")
    vegetables.add("Onion")
    vegetables.add("Cucumber")}
    private var nuts = ArrayList<String>()
    init {
        nuts.add("Almond")
        nuts.add("Cashew")
        nuts.add("Peanut")
        nuts.add("Pistachio")
        nuts.add("Walnut")
    }
    fun getData(): HashMap<String, List<String>> {
        item.put("Fruits", fruits)
        item.put("Vegetables", vegetables)
        item.put("Nuts", nuts)
        return item
    }
}