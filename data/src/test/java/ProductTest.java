import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProductTest {

    private Product product = new Product(1, "name", "producer", 10.0);

    @Test
    public void basicTest(){
        assertThat(product.getId(), is(1));
        assertThat(product.getName(), is("name"));
        assertThat(product.getProducer(), is("producer"));
        assertThat(product.getPrice(), is(10.0));
    }
}