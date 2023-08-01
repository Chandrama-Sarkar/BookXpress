package com.microservice.cart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.microservice.cart.Entity.CartItem;

@Repository                      
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	@Query("select cartitem from CartItem cartitem where cartitem.userId =:n")
	public List<CartItem> getCartItemByUserId(@Param("n") Integer userId);
	

	@Query("select sum(cartitem.price) from CartItem cartitem where cartitem.userId =:n")
	public int getTotalPrice(@Param("n") Integer userId);
	
	@Query("select item from CartItem item where item.bookId =:bid and item.userId =:uid")
	public CartItem getCartItemByUserIdAndBookId(@Param("bid") Integer bid, @Param("uid") Integer uid);
	
	@Query("select sum(cartitem.price)from CartItem cartitem where cartitem.userId=:n")
	public int getTotalAmount(@Param("n")Integer userId);
	

	
}
