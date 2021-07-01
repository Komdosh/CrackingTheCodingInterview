package animals.optimized.naive;

sealed class Animal permits AnimalShelter.Cat, AnimalShelter.Dog {
	private int order;
	
	public void setOrder(int ord) {
		order = ord;
	}
	
	public int getOrder() {
		return order;
	}
	
	public boolean isOlderThan(Animal a) {
		return this.order < a.getOrder();
	}
}