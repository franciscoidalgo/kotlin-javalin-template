package repository.impl

import dao.Dao
import model.User
import repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dao: Dao<User, Long>) : UserRepository {
    override fun findById(clazz: Class<User>, id: Long): User = dao.findById(clazz, id)

    override fun save(obj: User): Long = dao.save(obj)

    override fun update(obj: User) = dao.update(obj)

    override fun delete(obj: User) = dao.delete(obj)
}