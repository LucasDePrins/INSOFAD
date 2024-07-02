package com.example.powerflex.utils;

import com.example.powerflex.dao.OrderLineRepository;
import com.example.powerflex.dao.CustomOrderRepository;
import com.example.powerflex.dao.ProductDAO;
import com.example.powerflex.dao.UserRepository;
import com.example.powerflex.models.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Seeder {
    private ProductDAO productDAO;
    private UserRepository userRepository;
    private OrderLineRepository orderLineRepository;
    private CustomOrderRepository customOrderRepository;

    public Seeder(ProductDAO productDAO, UserRepository userRepository, OrderLineRepository orderLineRepository, CustomOrderRepository customOrderRepository) {
        this.productDAO = productDAO;
        this.userRepository = userRepository;
        this.orderLineRepository = orderLineRepository;
        this.customOrderRepository = customOrderRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event){
        this.seedProducts();
        this.seedUser();
//        this.seedOrderline();
    }

    private void seedProducts(){
        Category categoryWatches = new Category("Luxury Watches");
        Category categoryDesignerClothing = new Category("Designer Clothing");
        Category categoryExclusiveAccessories = new Category("Exclusive Accessories");

// Luxe horloges
        Product product1 = new Product("Patek Philippe Grandmaster Chime", "The most intricate wristwatch ever made by Patek Philippe, featuring 20 complications and a reversible case.", 2500000, categoryWatches, "https://example.com/patek-philippe-grandmaster.jpg");
        Product product2 = new Product("Rolex Daytona Rainbow", "Studded with sapphires in a rainbow formation, this Rolex Daytona combines luxury with a unique, colorful flair.", 340000, categoryWatches, "https://example.com/rolex-daytona-rainbow.jpg");
        Product product3 = new Product("Audemars Piguet Royal Oak Offshore", "Iconic octagonal design with a grand complication and impeccable craftsmanship.", 850000, categoryWatches, "https://example.com/audemars-piguet-royal-oak.jpg");

// Designerkleding
        Product product4 = new Product("Gucci Embroidered Velvet Blazer", "This blazer features intricate gold embroidery on velvet, exemplifying Gucci's iconic style and luxury.", 6500, categoryDesignerClothing, "https://example.com/gucci-blazer.jpg");
        Product product5 = new Product("Chanel Classic Suit", "A timeless Chanel tweed suit that offers elegance and sophistication with every detail.", 9000, categoryDesignerClothing, "https://example.com/chanel-classic-suit.jpg");
        Product product6 = new Product("Hermès Leather Jacket", "Crafted from the finest materials, this Hermès leather jacket sets the standard for luxury outerwear.", 15000, categoryDesignerClothing, "https://example.com/hermes-leather-jacket.jpg");

// Exclusieve accessoires
        Product product7 = new Product("Louis Vuitton Monogram Canvas Bag", "Featuring the classic LV monogram, this canvas bag represents the pinnacle of luxury in everyday fashion.", 5000, categoryExclusiveAccessories, "https://example.com/lv-monogram-bag.jpg");
        Product product8 = new Product("Cartier Love Bracelet", "Symbolizing timeless love, this Cartier bracelet is crafted in 18k gold and set with diamonds.", 15000, categoryExclusiveAccessories, "https://example.com/cartier-love-bracelet.jpg");
        Product product9 = new Product("Bvlgari Serpenti Diamond Necklace", "An exquisite piece that wraps around the neck with pavé diamonds, reflecting Bvlgari's craftsmanship.", 190000, categoryExclusiveAccessories, "https://example.com/bvlgari-serpenti-necklace.jpg");

// Bijwerken van bestaande productcreaties met luxe items
        this.productDAO.createProduct(product1);
        this.productDAO.createProduct(product2);
        this.productDAO.createProduct(product3);
        this.productDAO.createProduct(product4);
        this.productDAO.createProduct(product5);
        this.productDAO.createProduct(product6);
        this.productDAO.createProduct(product7);
        this.productDAO.createProduct(product8);
        this.productDAO.createProduct(product9);
    }

    private void seedUser(){
        CustomUser customUser = new CustomUser();
        customUser.setEmail("bob@bobsluxuryenterprise.com");
        customUser.setPassword(new BCryptPasswordEncoder().encode("IreallyL0vePupp1es!"));
        userRepository.save(customUser);
    }
}
