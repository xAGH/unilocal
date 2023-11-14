package eam.xagh.unilocal.auth.infrastructure.mappers

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import eam.xagh.unilocal.auth.domain.entities.User
import eam.xagh.unilocal.auth.domain.values.UserValue
import java.util.UUID

fun userToMap(user: User): Map<String, Any> {
    return mapOf(
        "isModerator" to user.isModerator,
        "city" to user.city.toString(),
        "name" to user.name,
        "email" to user.email,
        "nickname" to user.nickname,
        "password" to user.password
    )
}

fun documentSnapshotToUser(document: DocumentSnapshot): User {
    return UserValue(
        id = UUID.fromString(document.id),
        city = UUID.fromString(document.get("city").toString()),
        email = document.get("email").toString(),
        isModerator = document.get("city").toString().toBoolean(),
        name = document.get("name").toString(),
        nickname = document.get("nickname").toString(),
        password = document.get("password").toString()
    )
}
