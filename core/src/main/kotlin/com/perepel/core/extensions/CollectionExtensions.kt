package com.perepel.core.extensions

// Безопасное получение элемента
fun <T> List<T>.getOrNull(index: Int): T? {
    return if (index in indices) this[index] else null
}

// Проверка, содержит ли коллекция все элементы другой коллекции
fun <T> Collection<T>.containsAll(vararg elements: T): Boolean {
    return this.containsAll(elements.toList())
}

// Разделить список на пары (текущий и следующий)
fun <T> List<T>.pairWithNext(): List<Pair<T, T?>> {
    return this.mapIndexed { index, current ->
        current to this.getOrNull(index + 1)
    }
}