package eam.xagh.unilocal.shared.infrastructure.providers

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import eam.xagh.unilocal.auth.domain.entities.User
import eam.xagh.unilocal.auth.domain.values.UserValue
import eam.xagh.unilocal.shared.domain.services.AuthenticationService
import kotlinx.coroutines.runBlocking

class AuthenticationServiceAdapter constructor(context: Context) : AuthenticationService {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("ApplicationPreferences", Context.MODE_PRIVATE)

    private val userKey = "LAST_USER_LOGGED"
    private val auth = FirebaseAuth.getInstance()

    override val isAuthenticated: Boolean
        get() = auth.currentUser != null

    override fun closeSession() = auth.signOut()

    override fun setUser(user: User) {
        val gson = Gson()
        val json = gson.toJson(user);
        val editor = sharedPreferences.edit()
        editor.putString(userKey, json);
        editor.apply()
    }

    override val user: User?
        get() {
            val gson = Gson()
            val data = sharedPreferences.getString(userKey, "")
            if (data.isNullOrEmpty()) return null
            return try {
                gson.fromJson(data, UserValue::class.java)
            } catch (e: JsonSyntaxException) {
                null
            }
        }
}