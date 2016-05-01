import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ProductBase {
    private final Map<Product, Integer> productsMap = new HashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    public void addProduct(Product product, int quantity){
        lock.lock();
        try{
            if(productsMap.containsKey(product))
                productsMap.put(product, productsMap.get(product) + quantity);
            else
                productsMap.put(product, quantity);
        } finally {
            lock.unlock();
        }
    }

    public Map<Product, Integer> getProductsMap(){
        return new HashMap<>(productsMap);
    }

    public Map<Product, Integer> search(String word){
        return productsMap.entrySet().stream()
                .filter(p -> (p.getKey().getName().contains(word) || p.getKey().getProducer().contains(word)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public Map<Product, Integer> search (int id){
        return productsMap.entrySet().stream()
                .filter(p -> p.getKey().getId() == id)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public Map<Product, Integer> search (double price){
        return productsMap.entrySet().stream()
                .filter(p -> p.getKey().getPrice() == price)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public void removeProduct(){

    }
}
