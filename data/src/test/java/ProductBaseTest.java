import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ProductBaseTest {

    private ProductBase base;
    private Product product;

    @Before
    public void setUp() throws Exception {
        base = new ProductBase();
        product = new Product(1, "name", "producer", 10.0);
        base.addProduct(product, 5);
    }

    @Test
    public void addProduct() throws Exception {
        assertThat(base.getProductsMap().keySet()).containsOnly(product);
        assertThat(base.getProductsMap().get(product)).isEqualTo(5);
    }

    @Test
    public void addTwoProducts() throws Exception {
        Product product2 = new Product(2, "name2", "producer2", 20.0);
        base.addProduct(product2, 10);
        assertThat(base.getProductsMap().keySet()).containsOnly(product, product2);
        assertThat(base.getProductsMap().get(product)).isEqualTo(5);
        assertThat(base.getProductsMap().get(product2)).isEqualTo(10);
    }

    @Test
    public void addTwoProductsOfSameType() throws Exception{
        Product product2 = new Product(1, "name1", "producer1", 10.0);
        base.addProduct(product2, 10);
        assertThat(base.getProductsMap()).containsOnlyKeys(product);
        assertThat(base.getProductsMap().get(product)).isEqualTo(15);
    }

    @Test
    public void searchAndFindNothing() throws Exception{
        assertThat(base.search("wrongName")).isEmpty();
    }

    @Test
    public void searchAndFindResult() throws Exception{
        Map<Product, Integer> searchResult = base.search("name");
        assertThat(searchResult).containsOnlyKeys(product);
        assertThat(searchResult.get(product)).isEqualTo(5);
    }

    @Test
    public void searchWithMultipleResults() throws Exception{
        Product product2 = new Product(2, "something", "name", 15.0);
        base.addProduct(product2, 10);
        Map<Product, Integer> searchResult = base.search("name");
        assertThat(searchResult).containsOnlyKeys(product, product2);
        assertThat(searchResult.get(product)).isEqualTo(5);
        assertThat(searchResult.get(product2)).isEqualTo(10);
    }

    @Test
    public void searchById() throws Exception{
        assertThat(base.search(5)).isEmpty();
        assertThat(base.search(1)).containsOnlyKeys(product);
    }

    @Test
    public void searchByPrice() throws Exception{
        assertThat(base.search(15.0)).isEmpty();
        assertThat(base.search(10.0)).containsOnlyKeys(product);
    }
}