package app.a2ms.a2msnotes.db

/**
 *Created by Christophe Beyls
 * from https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e
 * */


open class SingletonHolder<out T, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    /**
     *Note that adding the [Volatile] annotation
     *to the instance field is required for the
     *algorithm to work properly.*/
    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}
