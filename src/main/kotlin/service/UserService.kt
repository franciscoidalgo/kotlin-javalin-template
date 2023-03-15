package service

import repository.UserRepository
import javax.inject.Inject

class UserService @Inject constructor(val userRepository: UserRepository) {
}