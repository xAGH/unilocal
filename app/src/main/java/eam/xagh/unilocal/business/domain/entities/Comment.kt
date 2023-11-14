package eam.xagh.unilocal.business.domain.entities

import java.util.UUID

interface Comment {
    val user: UUID
    val replies: List<Comment>
}