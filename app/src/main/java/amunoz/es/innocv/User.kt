package amunoz.es.innocv

import android.support.annotation.Nullable

data class User (
    val id: Int, // database entity
    @Nullable
    var name: String = "null",
    @Nullable
    val birthdate: String
)