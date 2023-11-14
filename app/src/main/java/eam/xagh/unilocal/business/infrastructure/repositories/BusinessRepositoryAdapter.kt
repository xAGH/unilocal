package eam.xagh.unilocal.business.infrastructure.repositories

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import eam.xagh.unilocal.business.application.usecases.list.GetBusinessCategoriesUseCase
import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.domain.repositories.BusinessRepository
import eam.xagh.unilocal.business.domain.values.BusinessValue
import eam.xagh.unilocal.business.infrastructure.mappers.businessToMap
import eam.xagh.unilocal.business.infrastructure.mappers.documentSnapshotToBusiness
import kotlinx.coroutines.tasks.await
import java.util.UUID

class BusinessRepositoryAdapter : BusinessRepository {
    val categories = GetBusinessCategoriesUseCase()
    override suspend fun getBusiness(owner: UUID): List<Business> {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("business")
        val business = mutableListOf<Business>()
        try {
            val documents =
                collection.whereEqualTo("owner", owner.toString()).get().await().documents
            documents.forEach { document ->
                business.add(documentSnapshotToBusiness(document))
            }
        } catch (e: Exception) {
            Log.i("ErrorBusiness", e.toString())
            return listOf()
        }
        return business
        /*return listOf(
            BusinessValue(
                name = "Negocio 1",
                description = "Negocio de comida",
                lat = 0.0,
                lon = 0.0,
                category = categories()[0].categoryName,
                phones = listOf(),
                comments = listOf(),
                schedule = listOf(),
                ratings = 3.1f,
                images = listOf(Uri.parse("https://img.freepik.com/free-photo/skyscraper-view-city-leader-window-frame_1134-1034.jpg"))
            ),
            BusinessValue(
                name = "Negocio 2",
                description = "Negocio de comida",
                lat = 0.0,
                lon = 0.0,
                category = categories()[0].categoryName,
                phones = listOf(),
                comments = listOf(),
                schedule = listOf(),
                ratings = 3.1f,
                images = listOf(Uri.parse("https://img.freepik.com/free-photo/skyscraper-view-city-leader-window-frame_1134-1034.jpg")),
                approved = true
            ),
            BusinessValue(
                name = "Negocio 3",
                description = "Negocio de comida",
                lat = 0.0,
                lon = 0.0,
                category = categories()[1].categoryName,
                phones = listOf(),
                comments = listOf(),
                schedule = listOf(),
                ratings = 3.1f,
                images = listOf(Uri.parse("https://img.freepik.com/free-photo/skyscraper-view-city-leader-window-frame_1134-1034.jpg"))
            ),
            BusinessValue(
                name = "Negocio 4",
                description = "Negocio de comida",
                lat = 0.0,
                lon = 0.0,
                category = categories()[2].categoryName,
                phones = listOf(),
                comments = listOf(),
                schedule = listOf(),
                ratings = 3.1f,
                images = listOf(Uri.parse("https://img.freepik.com/free-photo/skyscraper-view-city-leader-window-frame_1134-1034.jpg"))
            ),
        )*/
    }

    private suspend fun uploadImages(images: List<Uri>): List<Uri> {
        val storage = FirebaseStorage.getInstance()
        val urls = mutableListOf<Uri>()
        try {
            images.forEach {
                val url = storage
                    .reference
                    .child("business_images")
                    .child(UUID.randomUUID().toString())
                    .putFile(it).await()
                    .storage.downloadUrl.await()
                urls.add(url)
            }
        } catch (e: Exception) {
            Log.i("ErrorCreatingBusiness", e.toString())
        }
        return urls
    }

    override suspend fun createBusiness(business: Business): Boolean {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("business")
        val businessMap = businessToMap(business)
        val urls = uploadImages(business.images)
        businessMap["images"] = urls
        try {
            collection.document(business.id.toString()).set(businessMap).await()
        } catch (e: Exception) {
            Log.i("ErrorCreatingBusiness", e.toString())
            return false
        }
        return true
    }
}