class FoodRatings {
    private Map<String, Integer> foodToRating;
    private Map<String, String> foodToCuisine;
    private Map<String, TreeSet<String>> cuisineToFoods;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToRating = new HashMap<>();
        foodToCuisine = new HashMap<>();
        cuisineToFoods = new HashMap<>();

        for(int i=0; i<foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToRating.put(food, rating);
            foodToCuisine.put(food, cuisine);

            cuisineToFoods.computeIfAbsent(cuisine, k -> new TreeSet<>((a, b) -> {
                int ratingA = foodToRating.get(a);
                int ratingB = foodToRating.get(b);

                if(ratingA != ratingB) {
                    return Integer.compare(ratingB, ratingA);
                }

                return a.compareTo(b);
            })).add(food);
        }
    }
    
    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        
        TreeSet<String> foods = cuisineToFoods.get(cuisine);
        foods.remove(food);

        foodToRating.put(food, newRating);

        foods.add(food);
    }
    
    public String highestRated(String cuisine) {
        TreeSet<String> foods = cuisineToFoods.get(cuisine);
        return foods.first();
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */