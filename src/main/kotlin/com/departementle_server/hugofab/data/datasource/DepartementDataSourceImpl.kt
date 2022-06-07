package com.departementle_server.hugofab.data.datasource

import com.departementle_server.hugofab.data.model.Departement
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import org.litote.kmongo.MongoOperator
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import java.util.*

class DepartementDataSourceImpl(
    db: CoroutineDatabase
): DepartementDataSource {

    private val departementCollection = db.getCollection<Departement>()

    private var departement : Departement? = null
    private var dailyId: Int? = null
    private var lastGeneratedDate : Int? = null

    override suspend fun getDailyDepartement(): Departement {
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        // If new day
        if( lastGeneratedDate == null || lastGeneratedDate!! != currentDay) {
            // Update current day
            lastGeneratedDate = currentDay
            // Get new id
            val departements = departementCollection.find(Departement::guessed eq false).toList()
            this.dailyId = (departements.indices).random()
            // Update daily departement
            this.departement = departements[this.dailyId!!]
            // Update guessed value
            this.updateGuessedDepartement()
        }
        return this.departement!!
    }

    override suspend fun getAllDepartementName(): List<String> {
        return departementCollection.distinct<String>("name").toList()
    }

    override suspend fun tryDepartement(departementName: String): Boolean {
        return departementName == this.departement!!.name
    }


    private suspend fun updateGuessedDepartement() {
        departementCollection.updateOne(Filters.eq("id", this.departement!!.id), Updates.set("guessed", true) )
    }
}