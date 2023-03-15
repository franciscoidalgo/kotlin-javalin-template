package model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var publicKey: String? = null,
) {
    override fun toString(): String {
        return "User(id=$id, publicKey=$publicKey)"
    }
}