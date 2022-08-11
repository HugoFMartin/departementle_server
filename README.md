# Departementle Server

Server side for the **Departementle** game ( //url )
<br/>
Version **1.0**

# Endpoints

## /departement
Method : GET

Return each day a new department

    {
	    name: String, // Name of the department
	    img: String, // SVG Shape of the department
	    id: Integer // Daily departement ID
    }
## /departements
Method : GET

Return the list of department names
## /guess
Method: POST

Params

    {
	    departementName: String, // The department name to guess
	    id: Integer // Daily departement ID
    }

Return the result for the guessed department
	

    {
	    guess: {
			departement: String // The department name of the guessed departement
			distanceTo: Int // The distance, in km, from the guessed department to the daily department
			direction: String // The coordinate direction to the daily department
		},
		isValid: Boolean // True if the guessed department is the daily department
		id: Integer // Daily departement ID
    }

# Technos

Made with [Kotlin Ktor](https://ktor.io/)
Use [MongoDB](https://www.mongodb.com/) for database
Deployed to [Heroku](https://www.heroku.com/home) 

Developed by *HugoFMartin*
