package com.jsanhueza.prog0523.u1.restaurantorder.model

class ItemMesa(val itemMenu: ItemMenu, var cantidad: Int) {
    fun calcularSubtotal(): Int {
        return itemMenu.precio.toInt() * cantidad
    }
}
