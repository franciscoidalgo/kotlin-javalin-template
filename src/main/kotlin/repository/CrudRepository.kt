package repository

interface CrudRepository<T, ID> {
    fun findById(clazz: Class<T>, id: ID): T
    fun save(obj: T): ID
    fun update(obj: T)
    fun delete(obj: T)
}