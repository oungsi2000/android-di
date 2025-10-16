package woowacourse.fixture

import woowacourse.shopping.data.CartProductDao
import woowacourse.shopping.data.CartProductEntity

class FakeCartProductDao : CartProductDao {
    private val cartProducts = mutableListOf<CartProductEntity>()

    override suspend fun getAll(): List<CartProductEntity> = cartProducts

    override suspend fun insert(cartProduct: CartProductEntity) {
        cartProducts.add(cartProduct)
    }

    override suspend fun delete(id: Long) {
        cartProducts.removeIf { it.id == id }
    }
}
