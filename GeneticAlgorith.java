import java.util.Random;

class GeneticAlgorith {
    public static final int NORTH 	= 0;
    public static final int SOUTH 	= 2;
    public static final int EAST 	= 1;
    public static final int WEST    = 3;

	private int mutationRate;
	private int generation;

	private int[][] chromosomes;


	private int roadStones;
	private Table table;
	private Random rnd;

	public GeneticAlgorith(Table table, int generations, int population, int mutation) {
		this.roadStones = table.getRoad().size();
		this.table = table;
		rnd = new Random();
		
		mutationRate = mutation;
		this.generation = generations;
		
		chromosomes = new int[population][roadStones + 1];

		for(int i = 0; i < population; i++)
			for(int j = 0; j < roadStones-1; j++)
				chromosomes[i][j] = rnd.nextInt(4);

		for(int g = 0; g <= generation; g++) {
			table.setMessage(message(g,0));
			fitness(chromosomes);
		}
	}

	public static int eletism(int[][] chromosomes) {
		return 0;
	}

	public static void mutation() {	}

	private static void fitness(int[] a) { }
	// private static int fitness(int a) { return 0; }

	private static void crossover() { }

	private static String message(int g, int bf) {
		return
			"Generation: " + g + "\n"+
			"Best fitness: " + bf;
	}
}