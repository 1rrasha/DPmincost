package application;

//class to help to get path and cost of cites
class Path {
	// attributes
	private String path;
	private int cost;

	// constructor
	public Path(String path, int cost) {
		this.path = path;
		this.cost = cost;
	}

	// getters
	public String getPath() {
		return path;
	}

	public int getCost() {
		return cost;
	}
}