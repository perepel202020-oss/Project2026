package com.perepel.core.result

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    val isLeft: Boolean get() = this is Left
    val isRight: Boolean get() = this is Right

    fun fold(onLeft: (L) -> Unit, onRight: (R) -> Unit) {
        when (this) {
            is Left -> onLeft(value)
            is Right -> onRight(value)
        }
    }
}