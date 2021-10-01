import java.util.Random;

class GeneticAlgorith {
    public static final int NORTH 	= 0;
    public static final int SOUTH 	= 2;
    public static final int EAST 	= 1;
    public static final int WEST    = 3;

	private int mutationRate;
	private int generation;

	private int[] chromosomes;


	private int roadStones;
	private Table table;
	private Random rnd;

	public GeneticAlgorith(Table table, int population, int generation, int mutation) {
		this.roadStones = table.getRoad().size();
		this.table = table;
		rnd = new Random();
		
		mutationRate = mutation;
		generation = 0;
		
		chromosomes = new int[population][roadStones];

		for(int i = 0; i < population; i++)
			for(int j = 0; i < roadStones)
				chromosomes[i][j] = rnd.nextInt(4);

		for(int g = 0; g < generation; g++)	{
			table.setMessageWR(message(g,bestFit));
			fitness();
		}
	}

	public static int eletism(int[][] chromosomes) {
		return 0;
	}

	public static void mutation() {	}

	private static int fitness(int a) { return 0; }
	private static void fitness(int[] a) { }

	private static void crossover() { }

	private static String message(int g, int bf) {
		return
			"Generation: " + g +
			"Best fitness: " + bf;
	}
}