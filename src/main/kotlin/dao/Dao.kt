package dao

import org.hibernate.SessionFactory
import java.io.Serializable
import javax.inject.Inject


class Dao<T, ID : Serializable> @Inject constructor(private val sessionFactory: SessionFactory) {
    fun findById(clazz: Class<T>, id: ID): T {
        val session = sessionFactory.currentSession
        val transaction = session.beginTransaction()
        val result = session.find(clazz, id)
        transaction.commit()
        return result
    }

    fun save(obj: T): ID {
        val session = sessionFactory.currentSession
        val transaction = session.beginTransaction()
        val result = session.save(obj)
        transaction.commit()
        @Suppress("UNCHECKED_CAST")
        return result as ID
    }

    fun update(obj: T) {
        val session = sessionFactory.currentSession
        val transaction = session.beginTransaction()
        session.update(obj)
        transaction.commit()
    }

    fun delete(obj: T) {
        val session = sessionFactory.currentSession
        val transaction = session.beginTransaction()
        session.delete(obj)
        transaction.commit()
    }

    fun deleteById(clazz: Class<T>, id: ID, idColumn: String = "id"): Int {
        val session = sessionFactory.currentSession
        val transaction = session.beginTransaction()
        val hqlQuery = "DELETE FROM ${clazz.name} " +
                "WHERE $idColumn = $id"
        val query = session.createQuery(hqlQuery)
        val result = query.executeUpdate()
        transaction.commit()
        return result
    }

    fun executeQuery(hqlQuery: String): Int {
        val session = sessionFactory.currentSession
        val transaction = session.beginTransaction()
        val query = session.createQuery(hqlQuery)
        val result = query.executeUpdate()
        transaction.commit()
        return result
    }
}