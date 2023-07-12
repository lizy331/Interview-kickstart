package JavaInterviewQuestions.RestAPI.ShoppingCart.Dao;

import JavaInterviewQuestions.RestAPI.ShoppingCart.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {


}
