package com.java.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.PaymentDto;
import com.java.entity.CartEntity;
import com.java.entity.CartEntity.PaymentStatus;
import com.java.entity.ProductEntity;
import com.java.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	@Autowired
	CartService service;

	@PostMapping
	public ResponseEntity<CartEntity> createCart(@RequestBody CartEntity cart, HttpServletRequest req)
			throws URISyntaxException {
		long id = service.addCart(cart);
		return ResponseEntity.created(new URI(req.getRequestURI() + "/" + id)).build();
	}

	@PostMapping("/_item/{id}")
	public ResponseEntity<Object> addItem(@RequestBody ProductEntity product, @PathParam(value = "id") long id) {
		CartEntity cart = service.getCartById(id);
		cart.getProducts().add(product); // TODO: test this one.
		service.updateCart(cart);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/_item/{id}")
	public ResponseEntity<Object> removeItem(@RequestBody ProductEntity product, @PathParam(value = "id") long id) {
		CartEntity cart = service.getCartById(id);
		cart.getProducts().remove(product);
		service.updateCart(cart);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/_item/_all/{id}")
	public ResponseEntity<Object> removeAllItems(@PathParam(value = "id") long id) {
		CartEntity cart = service.getCartById(id);
		cart.getProducts().clear();
		service.updateCart(cart);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CartEntity> getCartById(@PathVariable long id) {
		CartEntity cart = service.getCartById(id);
		if (cart != null)
			return ResponseEntity.ok(cart);
		else
			return ResponseEntity.noContent().build();
	}

	@GetMapping("/_item/{id}")
	public ResponseEntity<List<ProductEntity>> getItemsById(@PathVariable long id) {
		CartEntity cart = service.getCartById(id);
		if (cart != null)
			return ResponseEntity.ok(cart.getProducts());
		else
			return ResponseEntity.noContent().build();
	}

	@GetMapping("/_empty/{id}")
	public ResponseEntity<Boolean> isCartEmpty(@PathVariable long id) {
		CartEntity cart = service.getCartById(id);
		if (cart != null && cart.getProducts().size() > 0)
			return ResponseEntity.ok(true);
		else
			return ResponseEntity.ok(false);
	}

	@PutMapping("/_checkout/{id}")
	public ResponseEntity<Long> checkoutCart(@PathParam(value = "id") long id) {
		CartEntity cart = service.getCartById(id);
		cart.setTransactionId(cart.getId());
		cart.setPaymentStatus(PaymentStatus.PENDING);
		service.updateCart(cart);
		return ResponseEntity.ok(cart.getTransactionId());
	}

	@PutMapping("/_paymentStatus/{id}")
	public ResponseEntity<Long> updatePaymentStatus(@RequestBody CartEntity paymentInfo,
			@PathParam(value = "id") long id) {
		CartEntity cart = service.getCartById(id);
		cart.setTransactionId(paymentInfo.getTransactionId());
		cart.setPaymentStatus(paymentInfo.getPaymentStatus());
		service.updateCart(cart);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/_payment", params = "username")
	public ResponseEntity<List<PaymentDto>> getPaymentsByUsername(@RequestParam String username) {
		List<CartEntity> carts = service.findByUsername(username);
		if (carts != null) {
			List<PaymentDto> payments = new ArrayList<>();
			for (CartEntity cart : carts) {
				payments.add(new PaymentDto(cart.getTransactionId(), cart.getPaymentStatus()));
			}
			return ResponseEntity.ok(payments);
		} else
			return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/_payment", params = "id")
	public ResponseEntity<PaymentDto> getPaymentsByCart(@RequestParam long id) {
		CartEntity cart = service.getCartById(id);
		if (cart != null) {
			PaymentDto payment = new PaymentDto(cart.getTransactionId(), cart.getPaymentStatus());
			return ResponseEntity.ok(payment);
		} else
			return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/_payment", params = "username, paymentStatus")
	public ResponseEntity<List<PaymentDto>> getPaymentsByUsernameAndPaymentStatus(@RequestParam String username, @RequestParam PaymentStatus paymentStatus) {
		List<CartEntity> carts = service.findByUsernameAndPaymentStatus(username, paymentStatus);
		if (carts != null) {
			List<PaymentDto> payments = new ArrayList<>();
			for (CartEntity cart : carts) {
				payments.add(new PaymentDto(cart.getTransactionId(), cart.getPaymentStatus()));
			}
			return ResponseEntity.ok(payments);
		} else
			return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/_payment", params = "paymentStatus")
	public ResponseEntity<List<PaymentDto>> getPaymentsByPaymentStatus(@RequestParam PaymentStatus paymentStatus) {
		List<CartEntity> carts = service.findByPaymentStatus(paymentStatus);
		if (carts != null) {
			List<PaymentDto> payments = new ArrayList<>();
			for (CartEntity cart : carts) {
				payments.add(new PaymentDto(cart.getTransactionId(), cart.getPaymentStatus()));
			}
			return ResponseEntity.ok(payments);
		} else
			return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/_payment")
	public ResponseEntity<List<PaymentDto>> getAllPayments() {
		List<CartEntity> carts = service.getAll();
		if (carts != null) {
			List<PaymentDto> payments = new ArrayList<>();
			for (CartEntity cart : carts) {
				payments.add(new PaymentDto(cart.getTransactionId(), cart.getPaymentStatus()));
			}
			return ResponseEntity.ok(payments);
		} else
			return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/_quantity/{id}")
	public ResponseEntity<Object> updateQuantity(@RequestBody ProductEntity product, @PathParam(value = "id") long id) {
		CartEntity cart = service.getCartById(id);
		List<ProductEntity> products = cart.getProducts();
		for (ProductEntity p : products) {
			if (p.getProductId() == product.getProductId()) {
				p.setProductQuantity(product.getProductQuantity());
				break;
			}
		}
		cart.setProducts(products);
		service.updateCart(cart);
		return ResponseEntity.ok().build();
	}
}
