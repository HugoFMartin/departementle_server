package com.departementle_server.hugofab.data.datasource


import com.departementle_server.hugofab.data.model.Departement
import com.departementle_server.hugofab.data.model.DepartementDTO
import com.departementle_server.hugofab.data.response.GuessResponse
import com.departementle_server.hugofab.data.utils.GeomUtils
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import java.util.*

class DepartementDataSourceImpl(
    db: CoroutineDatabase
): DepartementDataSource {

    private val departementCollection = db.getCollection<Departement>()

    private var departement : Departement? = null
    private var lastGeneratedDate : Int? = null


    /**
     *
     */
    override suspend fun getDailyDepartementDTO(): DepartementDTO {
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        // If new day
        if( lastGeneratedDate == null || lastGeneratedDate!! != currentDay) {
            // Update current day
            lastGeneratedDate = currentDay
            // Get new id
            val departements = departementCollection.find(Departement::guessed eq false).toList()
            val dailyId = (departements.indices).random()
            // Update daily departement
            this.departement = departements[dailyId]
            // Update guessed value
            this.updateGuessedDepartement()
        }

        return this.departement!!.toDepartementDTO()
    }

    /**
     *
     */
    override suspend fun getAllDepartementName(): List<String> {
        return departementCollection.distinct<String>("name").toList()
    }

    /**
     *
     */
    override suspend fun guessDepartement(departementName: String): GuessResponse {
        if (departementName == this.departement!!.name) {
            return GuessResponse(1,departementName,true,0,  "" )
        }
        val guessDepartement = departementCollection.find(Departement::name eq departementName).toList().first()

        val distanceFromGuessDepartement = GeomUtils.getDistanceFromTo(
            guessDepartement.lat,
            guessDepartement.long,
            this.departement!!.lat,
            this.departement!!.long,
        )

        val directionFromGuessDepartement = GeomUtils.getDirectionFromTo(
            guessDepartement.long,
            guessDepartement.lat,
            this.departement!!.long,
            this.departement!!.lat
        )

        return GuessResponse(1,departementName,false,distanceFromGuessDepartement,directionFromGuessDepartement)
    }

    private suspend fun updateGuessedDepartement() {
        departementCollection.updateOne(Filters.eq("id", this.departement!!.id), Updates.set("guessed", true) )
    }
}