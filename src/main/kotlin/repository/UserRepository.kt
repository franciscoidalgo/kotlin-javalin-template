package repository

import model.User

interface UserRepository : CrudRepository<User, Long>