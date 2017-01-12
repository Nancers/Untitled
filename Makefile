JC = javac

all: PokemonGame

PokemonGame:
	$(JC) LevelRate.java Move.java Nature.java Naturedex.java Player.java Pokedex.java Pokemon.java PokemonGame.java PokemonMap.java PokemonWorld.java Type.java

clean:
	$(RM) *.class