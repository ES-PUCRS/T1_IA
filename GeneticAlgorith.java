import java.util.Random;

class GeneticAlgorith {
    public static final int NORTH 	= 0;
    public static final int SOUTH 	= 2;
    public static final int EAST 	= 1;
    public static final int WEST    = 3;

	private int mutationRate;
	private int generation;

	private Table[][] population;
	private Table[][] newGeneration;


	private int roadStones;
	private Table table;
	private Random rnd;

	public GeneticAlgorith(Table table, int generations, int populationSize, int mutation) {
		this.roadStones = table.getRoad().size();
		this.table = table;
		rnd = new Random();
		
		mutationRate = mutation;
		this.generation = generations;
		
		population 	  = new int[populationSize][roadStones + 1];
		newGeneration = new int[populationSize][roadStones + 1];

		for(int i = 0; i < population; i++)
			for(int j = 0; j < roadStones-1; j++)
				chromosomes[i][j] = rnd.nextInt(4);

		for(int g = 0; g <= generation; g++) {
			table.setMessage( message(g, 0) );
			fitness(population);

			sout(population);

			transfer(population, eletism(population), newGeneration, 0);
			crossover(newGeneration, population);

			if(rnd.nextInt(100) < mutationRate)
				mutation(newGeneration);

			population = newGeneration;
		}
	}



	public static int eletism(int[][] population) {
		int score = population[0].length - 1;
		int smallest = population[0][score];
		int lowest = 0;

		for(int i = 0; i < population.length; i++) {
			if(smallest > population[i][score]) {
				smallest = population[i][score];
				lowest = i;
			}
		}

		return lowest;
	}

	public static void mutation(int[][] population) {
		int line = rnd.nextInt(population.length);
		int column = rnd.nextInt(population[0].length - 1);
		
		if(population[line][column] == 0)
			population[line][column] = 1;
		else
			population[line][column] = 0;
	}

	private static void fitness(int[][] chromosomes) { }
	private static int fitness(int a) { return 0; }

	private static transfer(int[][] from, int pos, int[][] to, int ps) {
		for(int i = 0; i < from[0].length; i++) {
			to[ps][i] = from[pos][i];
		}
	}

	public static int tournament(int[][] population) {
		int father 	= rnd.nextInt(population.length);
		int dad 	= rnd.nextInt(population.length);
		int score 	= population[0].length - 1;
		if(population[father][score] < population[dad][score]){
			return father;
		}
		return dad;
	}

	private static void crossover(int[][] newGeneration, int[][] population) {
		for(int i = 1; i < population.length; i+=2) {
			int c1 = tournament(population);
			int c2 = tournament(population);

			for(int j=0; j < population[i].length - 1; j++) {
				if(j < population[i].length / 2){
					newGeneration[i][j] = population[c1][j];
					newGeneration[i+1][j] = population[c2][j];
				}else{
					newGeneration[i][j] = population[c2][j];
					newGeneration[i+1][j] = population[c1][j];
				}
			}
		}
	}

	private static String message(int g, int bf) {
		return
			"Generation: " + g + "\n"+
			"Best fitness: " + bf;
	}
}