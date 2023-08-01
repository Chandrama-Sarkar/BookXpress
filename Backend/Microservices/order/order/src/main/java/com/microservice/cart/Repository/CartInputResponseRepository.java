package com.microservice.cart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.cart.Entity.CartInputResponseDTO;
import com.microservice.cart.Entity.CartItem;
@Repository
public interface CartInputResponseRepository extends JpaRepository<CartInputResponseDTO,Integer>{

	@Query("select item from CartInputResponseDTO item where item.bookId =:bid and item.userId =:uid")
	public  CartInputResponseDTO getCartItemByUserIdAndBookId(@Param("bid") Integer bid, @Param("uid") Integer uid);
	
	@Query("select cartitem from CartInputResponseDTO cartitem where cartitem.userId =:n")
	public List<CartInputResponseDTO> getCartItemByUserId(@Param("n") Integer userId);
	

	@Query("select sum(cartitem.price) from CartInputResponseDTO cartitem where cartitem.userId =:n")
	public int getTotalPrice(@Param("n") Integer userId);
	
	@Query("select count(cartitem)from CartInputResponseDTO cartitem where cartitem.userId=:n")
	public int getCartItemCount(@Param("n")Integer userId);
}
