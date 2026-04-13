package com.jsanhueza.prog0523.u1.restaurantorder.model

class CuentaMesa(val mesa: Int) {
    private val _items: MutableList<ItemMesa> = mutableListOf()
    var aceptaPropina: Boolean = true

    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        val itemMesa = ItemMesa(itemMenu, cantidad)
        _items.add(itemMesa)
    }

    fun agregarItem(itemMesa: ItemMesa) {
        _items.add(itemMesa)
    }

    fun calcularTotalSinPropina(): Int {
        var total = 0
        for (item in _items) {
            total += item.calcularSubtotal()
        }
        return total
    }

    fun calcularPropina(): Int {
        return if (aceptaPropina) {
            (calcularTotalSinPropina() * 0.1).toInt()
        } else {
            0
        }
    }

    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + calcularPropina()
    }
}
