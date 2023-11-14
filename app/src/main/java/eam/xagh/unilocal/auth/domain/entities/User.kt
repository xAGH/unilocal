package eam.xagh.unilocal.auth.domain.entities

import java.util.UUID

interface User {
    val id: UUID
    val name: String
    val email: String
    val password: String
    val city: UUID
    val nickname: String
    val isModerator: Boolean
}