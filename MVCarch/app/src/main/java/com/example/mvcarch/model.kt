package com.example.mvcarch

import java.util.Observable
import kotlin.collections.ArrayList

class model : Observable() {
    val list : MutableList<Int>
    init {
        list = ArrayList(3)
        list.add(0)
        list.add(0)
        list.add(0)
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getter(index : Int) : Int {
        return list[index]
    }

    @Throws(IndexOutOfBoundsException::class)
    fun setter(index : Int) {
        list[index] = list[index] + 1
        setChanged()
        notifyObservers()
    }
}