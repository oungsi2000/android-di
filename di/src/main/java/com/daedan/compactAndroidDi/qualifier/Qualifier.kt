package com.daedan.compactAndroidDi.qualifier

interface Qualifier {
    override fun toString(): String

    override fun hashCode(): Int

    override fun equals(other: Any?): Boolean
}
