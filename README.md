# PokedexApi
This API returns a list of pokemons.

### Knapsack problem
This API must consume the PokeApi service https://pokeapi.co/ to obtain the information. The documentation can be found at https://pokeapi.co/docs/v2

From the frontend side we will only need the different calls by curl or postman (this is part of the deliverable) to the exposed APIs and be able to obtain for each of the pokemon (photo and its basic information):
- Type (type)
- Weight (weight)
- List of Skills (ability)

Also, when clicking on one, its descriptive file should be shown along with its photo and detailed information:
- Basic Information (the same as the list)
- Evolutions


### API Request
##### local: `http://localhost:8080/pokemons/0`
##### Heroku: `https://poke-api-123.herokuapp.com/api/pokemons/0`
Body `application/json`

```
{"next":"https://poke-api-123.herokuapp.com/api/pokemons/1",
	"previous":null,
	"results" :[{ "id":1,
	"weight":69,
	"image":"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
	"name":"bulbasaur",
	"type":["grass","poison"],
	"abilities":["overgrow","chlorophyll"],
	"evolutions":null}]
}
```

### API Request
##### local: `http://localhost:8080/pokemon/1`
##### Heroku: `https://poke-api-123.herokuapp.com/api/pokemon/1`
Body `application/json`

```
{
	"id":1,
	"weight":69,
	"image":"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
	"name":"bulbasaur",
	"type":["grass","poison"],
	"abilities":["overgrow","chlorophyll"],
	"evolutions":[{ 		
		"id":2,
		"weight":130,
		"image":"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
		"name":"ivysaur",
		"type":["grass","poison"],
		"abilities":["overgrow","chlorophyll"],
		"evolutions":null},
		{
		"id":3,
		"weight":1000,
		"image":"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
		"name":"venusaur",
		"type":["grass","poison"],
		"abilities":["overgrow","chlorophyll"],
		"evolutions":null
		}]
}
```
### Install the Heroku CLI
Download and install the Heroku CLI.

If you haven't already, log in to your Heroku account and follow the prompts to create a new SSH public key.
```
$ heroku login
```
### Clone the repository
Use Git to clone poke-api-123's source code to your local machine.


```
$ heroku git:clone -a poke-api-123
$ cd poke-api-123

```
###Deploy your changes
Make some changes to the code you just cloned and deploy them to Heroku using Git.
```
$ git add .
$ git commit -am "make it better"
$ git push heroku master
```