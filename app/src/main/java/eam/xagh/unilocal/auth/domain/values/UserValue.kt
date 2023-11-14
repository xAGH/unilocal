package eam.xagh.unilocal.auth.domain.values

import eam.xagh.unilocal.auth.domain.entities.User
import eam.xagh.unilocal.core.domain.entities.City
import java.util.UUID

data class UserValue(
    override val name: String,
    override val email: String,
    override val password: String,
    override val city: UUID,
    override val nickname: String,
    override val isModerator: Boolean = false,
    override val id: UUID = UUID.randomUUID()
) : User