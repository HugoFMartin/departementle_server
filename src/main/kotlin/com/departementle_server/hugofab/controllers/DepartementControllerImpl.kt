package com.departementle_server.hugofab.controllers


import com.departementle_server.hugofab.data.model.*
import com.departementle_server.hugofab.utils.Direction
import com.departementle_server.hugofab.utils.GeomUtils
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import java.util.*

class DepartementControllerImpl(
    db: CoroutineDatabase
): DepartementController {

    private val departementCollection = db.getCollection<Departement>()

    private var departement: Departement? = null
    private var id: Int = 0
    private var lastGeneratedDate: Int? = null


    /**
     *
     */
    override suspend fun getDailyDepartement(): HttpResponse<DepartementDTO> {
        try {
            val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            // If new day
            if( lastGeneratedDate == null || lastGeneratedDate!! != currentDay) {
                // Update current day
                lastGeneratedDate = currentDay
                // Update id
                this.id ++
                // Get new random id
                val departements = departementCollection.find(Departement::guessed eq false).toList()
                val dailyId = (departements.indices).random()
                // Update daily departement
                this.departement = departements[dailyId]
                // Update guessed value
                this.updateGuessedDepartement()
            }
            val data = DepartementDTO(this.departement!!.name, this.departement!!.img, this.id)
            return HttpResponse.Success(data)
        } catch (err: Throwable) {
            return HttpResponse.Error("Error: Database error")
        }
    }

    /**
     *
     */
    override suspend fun getAllDepartementName(): HttpResponse<List<String>> {
        try {
            return return HttpResponse.Success(departementCollection.distinct<String>("name").toList())
        } catch (err: Throwable) {
            return HttpResponse.Error("Error: Database error")
        }

    }

    /**
     *
     */
    override suspend fun guessDepartement(requestBody: GuessRequest): HttpResponse<GuessDTO> {
        // Daily departement is not defined
        if (this.departement == null) {
            return HttpResponse.Error("Error: No daily departement defined", )
        }
        // Client has wrong id
        if (requestBody.id != this.id) {
            return HttpResponse.Error("Error: Wrong id, must refresh your page")
        }
        // Guess is valid
        if (requestBody.departementName == this.departement!!.name) {
            val data = GuessDTO(
                Guess(requestBody.departementName,0, Direction.guessed),
                true,
                this.id,
            )
            return HttpResponse.Success(data)
        }

        try {
            val guessDepartement = departementCollection.find(Departement::name eq requestBody.departementName).toList().first()

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

            val data = GuessDTO(
                Guess(requestBody.departementName,distanceFromGuessDepartement, directionFromGuessDepartement),
                false,
                this.id,
            )
            return HttpResponse.Success(data)
        } catch (err: Throwable) {
            return HttpResponse.Error("Error: Database error")
        }
    }

    private suspend fun updateGuessedDepartement() {
        departementCollection.updateOne(Filters.eq("id", this.departement!!.id), Updates.set("guessed", true) )
    }
}