class MovieRentingSystem {
    private Map<Integer, TreeSet<Shop>> unrentedMovies;    
    private TreeSet<RentedMovie> rentedMovies;
    
    private int[][] prices; 
    private Map<Long, Integer> priceMap;

    private static class Shop implements Comparable<Shop> {
        int price, shop;
        
        Shop(int price, int shop) {
            this.price = price;
            this.shop = shop;
        }
        
        @Override
        public int compareTo(Shop other) {
            if (this.price != other.price) return Integer.compare(this.price, other.price);
            return Integer.compare(this.shop, other.shop);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Shop)) return false;
            Shop other = (Shop) obj;
            return this.price == other.price && this.shop == other.shop;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(price, shop);
        }
    }
    
    private static class RentedMovie implements Comparable<RentedMovie> {
        int price, shop, movie;
        
        RentedMovie(int price, int shop, int movie) {
            this.price = price;
            this.shop = shop;
            this.movie = movie;
        }
        
        @Override
        public int compareTo(RentedMovie other) {
            if (this.price != other.price) return Integer.compare(this.price, other.price);
            if (this.shop != other.shop) return Integer.compare(this.shop, other.shop);
            return Integer.compare(this.movie, other.movie);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof RentedMovie)) return false;
            RentedMovie other = (RentedMovie) obj;
            return this.price == other.price && this.shop == other.shop && this.movie == other.movie;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(price, shop, movie);
        }
    }
    
    public MovieRentingSystem(int n, int[][] entries) {
        unrentedMovies = new HashMap<>();
        rentedMovies = new TreeSet<>();
        priceMap = new HashMap<>();
        
        for (int[] entry : entries) {
            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];
            
            unrentedMovies.computeIfAbsent(movie, k -> new TreeSet<>())
                         .add(new Shop(price, shop));
            
            priceMap.put(((long) shop << 32) | movie, price);
        }
    }
    
    public List<Integer> search(int movie) {
        List<Integer> result = new ArrayList<>();
        TreeSet<Shop> available = unrentedMovies.get(movie);
        
        if (available != null) {
            Iterator<Shop> it = available.iterator();
            for (int i = 0; i < 5 && it.hasNext(); i++) {
                result.add(it.next().shop);
            }
        }
        
        return result;
    }
    
    public void rent(int shop, int movie) {
        int price = priceMap.get(((long) shop << 32) | movie);
        
        TreeSet<Shop> available = unrentedMovies.get(movie);
        if (available != null) {
            available.remove(new Shop(price, shop));
        }
        
        rentedMovies.add(new RentedMovie(price, shop, movie));
    }
    
    public void drop(int shop, int movie) {
        int price = priceMap.get(((long) shop << 32) | movie);        
        rentedMovies.remove(new RentedMovie(price, shop, movie));
        unrentedMovies.computeIfAbsent(movie, k -> new TreeSet<>())
                     .add(new Shop(price, shop));
    }
    
    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        Iterator<RentedMovie> it = rentedMovies.iterator();
        
        for (int i = 0; i < 5 && it.hasNext(); i++) {
            RentedMovie rm = it.next();
            result.add(Arrays.asList(rm.shop, rm.movie));
        }
        
        return result;
    }
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */