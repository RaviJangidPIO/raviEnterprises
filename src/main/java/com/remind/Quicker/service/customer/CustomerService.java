package com.remind.Quicker.service.customer;

import com.remind.Quicker.dto.responseDto.OrderCartResponseDetails;
import com.remind.Quicker.entities.CustomUser;
import com.remind.Quicker.entities.ItemOrdered;
import com.remind.Quicker.entities.OrderStatus;
import com.remind.Quicker.entities.Product;
import com.remind.Quicker.repository.CustomUserRepository;
import com.remind.Quicker.repository.ItemOrderedRepository;
import com.remind.Quicker.repository.OrderStatusRepository;
import com.remind.Quicker.repository.ProductRepository;
import com.remind.Quicker.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private OrderStatus orderStatus = new OrderStatus();
    private List<ItemOrdered> itemOrdereds = new ArrayList<>();
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private ItemOrderedRepository itemOrderedRepository;


    public void updateCart(Long id,Long pieces) {
        ItemOrdered currentItem = new ItemOrdered();
        Optional<Product> purchesingOrder = productRepository.findById(id);
        purchesingOrder.ifPresent((product)->{
            currentItem.setPiecesOrdered(pieces);
            currentItem.setPrice(product.getPrice());
            currentItem.setProduct(product);
        });
        itemOrdereds.add(currentItem);
    }

    public boolean confirmedOrder(CustomUser customer){
        orderStatus.setCategoryOrder((long) itemOrdereds.size());
        orderStatus.setCustomer(customer);
        orderStatus.setOrderPlacedAt(LocalDateTime.now());
        orderStatus.setStatus(Status.PROCESSING.toString());
        orderStatusRepository.save(orderStatus);
        OrderStatus orderStatus1 = orderStatusRepository.findRecentEntry();
        itemOrdereds.forEach(itemOrdered -> {
            itemOrdered.setOrderStatus(orderStatus1);
        });
        itemOrderedRepository.saveAll(itemOrdereds);
        return true;
    }

    public List<OrderCartResponseDetails> getPlacedProduct() {

        List<OrderCartResponseDetails> placedOrderList = new ArrayList<>();

        itemOrdereds.forEach(itemOrdered -> {
            OrderCartResponseDetails currentItem = new OrderCartResponseDetails();
            currentItem.setName(itemOrdered.getProduct().getName());
            currentItem.setPrice(itemOrdered.getProduct().getPrice());
            currentItem.setProductImage(itemOrdered.getProduct().getProductImage());
            currentItem.setOrderedPieces(itemOrdered.getPiecesOrdered());
            currentItem.setTotalPrice(currentItem.getPrice()* currentItem.getOrderedPieces());
            placedOrderList.add(currentItem);
        });

        return placedOrderList;

    }
}
