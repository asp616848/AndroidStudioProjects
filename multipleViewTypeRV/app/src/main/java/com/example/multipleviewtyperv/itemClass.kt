package com.example.multipleviewtyperv

class itemClass {
    var text_one: String = ""
    var text_two: String = ""
    var image:Int = 0
    private val layout_one = 0
    private val layout_two = 1
    var viewType: Int = 0
    constructor(text_one: String, text_two: String, image: Int, viewType: Int) {
        this.text_one = text_one
        this.text_two = text_two
        this.image = image
        this.viewType = viewType
    }
    constructor(text_one: String, viewType: Int) {
        this.text_one = text_one
        this.viewType = viewType
    }

}