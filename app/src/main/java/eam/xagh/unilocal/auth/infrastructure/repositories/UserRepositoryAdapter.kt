package eam.xagh.unilocal.auth.infrastructure.repositories

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import eam.xagh.unilocal.R
import eam.xagh.unilocal.auth.domain.entities.User
import eam.xagh.unilocal.auth.domain.repositories.UserRepository
import eam.xagh.unilocal.auth.domain.values.LoginResponse
import eam.xagh.unilocal.auth.domain.values.RegisterResponse
import eam.xagh.unilocal.auth.domain.values.UserValue
import eam.xagh.unilocal.auth.infrastructure.mappers.documentSnapshotToUser
import eam.xagh.unilocal.auth.infrastructure.mappers.userToMap
import eam.xagh.unilocal.core.infrastructure.repositories.CityRepositoryAdapter
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRepositoryAdapter : UserRepository {

    private val cities = CityRepositoryAdapter().getCities()

    private val users: MutableList<User> = mutableListOf(
        UserValue(
            "admin",
            "admin@gmail.com",
            "password",
            cities.find { it.name == "Armenia" }!!.id,
            "admin",
            isModerator = true
        ), UserValue(
            "user",
            "user@gmail.com",
            "password",
            cities.find { it.name == "Armenia" }!!.id,
            "user",
            isModerator = false
        )
    )

    private suspend fun emailAlreadyExists(email: String): Boolean {
        return suspendCoroutine { continuation ->
            val db = FirebaseFirestore.getInstance();
            val collection = db.collection("user")
            val emailFilter = collection.whereEqualTo("email", email)
            emailFilter.get().addOnCompleteListener() { task ->
                Log.i("Completado email", task.result.documents.toString())
                continuation.resume(task.result.documents.isNotEmpty())
            }
        }
    }

    override suspend fun registerUser(user: User, context: Context): RegisterResponse {
        val db = FirebaseFirestore.getInstance();
        val collection = db.collection("user")
        if (emailAlreadyExists(user.email)) return RegisterResponse(
            false, context.resources.getString(R.string.email_already_exists)
        )
        val addUser = collection.document(user.id.toString()).set(userToMap(user))
        return suspendCoroutine { continuation ->
            addUser.addOnCompleteListener { task ->
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email, user.password)
                continuation.resume(RegisterResponse(task.isSuccessful, task.exception?.message))
            }
        }
    }

    override suspend fun getUser(field: String, value: Any): User? {
        val db = FirebaseFirestore.getInstance()
        return suspendCoroutine { continuation ->
            db.collection("user").whereEqualTo(field, value).get()
                .addOnCompleteListener { task ->
                    val result = task.result.documents
                    if (result.isEmpty()) continuation.resume(null)
                    else continuation.resume(documentSnapshotToUser(result[0]))

                }
        }
    }

    override suspend fun sendRecoveryCode(nicknameOrEmail: String): Boolean {
        return nicknameOrEmail in listOf(
            "test11", "test22", "test33", "alejito23001@gmail.com"
        )
    }

    override suspend fun recoveryPassword(nicknameOrEmail: String, newPassword: String): Boolean {
        return true
    }

    override suspend fun signIn(emailOrNickname: String, password: String): LoginResponse {
        val isEmail = emailOrNickname.contains('@')
        var value = emailOrNickname
        val failed = LoginResponse(false, null)
        val user: User?
        if (!isEmail) {
            user = getUser("nickname", emailOrNickname) ?: return failed
            value = user.email
        } else user = getUser("email", emailOrNickname)
        return suspendCoroutine { continuation ->
            FirebaseAuth.getInstance().signInWithEmailAndPassword(value, password)
                .addOnCompleteListener { task ->
                    continuation.resume(
                        if (task.isSuccessful) LoginResponse(
                            true,
                            user
                        ) else failed
                    )
                }
        }
    }
}