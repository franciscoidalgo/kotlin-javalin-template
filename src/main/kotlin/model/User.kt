package model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


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