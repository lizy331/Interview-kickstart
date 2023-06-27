package PracticalExperiments.RestAPI.ShoppingCart;

import PracticalExperiments.RestAPI.ShoppingCart.Dao.ProductRepository;
import PracticalExperiments.RestAPI.ShoppingCart.Dao.ShoppingCartRepository;
import PracticalExperiments.RestAPI.ShoppingCart.Entity.Product;
import PracticalExperiments.RestAPI.ShoppingCart.Entity.ShoppingCart;
import com.beust.jcommander.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class ShoppingCartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @PostMapping
    private ResponseEntity<?> addProductsToCart(@PathVariable Long productId, @PathVariable Long cartId) throws Exception {

        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception("Product not found with id: " + productId));

        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElseThrow(() -> new Exception("Shopping cart not found with id: " + cartId));

        shoppingCart.getProducts().add(product);

        shoppingCartRepository.save(shoppingCart);

        return ResponseEntity.ok().build();
    }
}
