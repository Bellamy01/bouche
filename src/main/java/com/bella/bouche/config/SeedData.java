package com.bella.bouche.config;

import com.bella.bouche.models.Account;
import com.bella.bouche.models.Post;
import com.bella.bouche.services.AccountService;
import com.bella.bouche.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        // Account data
        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()) {
            seedAccounts();
        }

        // Post data
        List<Post> posts = postService.getAllPosts();
        if (posts.isEmpty()) {
            seedPosts();
        }
    }

    private void seedPosts() {
        System.out.println("Seeding post data...");
        Optional<Account> acc1 = accountService.getAccountById(1L);
        Optional<Account> acc2 = accountService.getAccountById(2L);

        if (acc2.isPresent() && acc1.isPresent()) {
            List.of(
                    new Post("Up-cycling Furniture on a Budget", "Breathe new life into your old furniture! This post explores creative and budget-friendly up-cycling techniques that anyone can try. Learn how to transform dated pieces into stunning statement features for your home, with tips on painting, reupholstering, and adding unique details.", LocalDateTime.now(), acc1.get()),
                    new Post("Mastering the Art of Meal Prep", " Feeling overwhelmed by nightly meal prep? This post unveils the secrets to successful meal prepping. We'll guide you through planning delicious and healthy meals for the week, prepping ingredients efficiently, and storing them for maximum freshness. Discover time-saving hacks and overcome common meal prep challenges, putting you on track for stress-free and healthy eating.", LocalDateTime.now(), acc1.get()),
                    new Post("The Ultimate Guide to Indoor Herb Gardening", "Liven up your kitchen and enjoy fresh herbs year-round with this comprehensive guide to indoor herb gardening. We'll cover everything you need to know, from choosing the right herbs to ideal planting conditions, watering schedules, and harvesting tips. Learn how to create a thriving indoor herb garden, adding a touch of green and delicious flavor to your cooking.", LocalDateTime.now(), acc2.get())
            ).forEach(postService::savePost);
            System.out.println("Post data seeded successfully!");
        } else {
            System.out.println("Error seeding post data: Account not found.");
        }
    }

    private void seedAccounts() {
        System.out.println("Seeding account data...");
        List.of(
                new Account("ndayishimiye", "test$123", "gilbert@test.com", "Gilbert","Ndayishimiye", "Beyond the perfect bubble, Gilbert, manager at Bubble Tea, knows the magic of a well-crafted drink. Join him as he explores the world of unique flavor combinations. ✨","Manager", "Bubble Tea" ,LocalDateTime.now()),
                new Account("manzi_cedrick", "test$123", "cedrick@test.com", "Cedrick","Manzi","Master Chef at Marriott Cuisines, Cedric brings global flavors to your table. With a passion for exploring culinary traditions, he's always looking to elevate your dining experience.","Chef","Marriott Cuisines", LocalDateTime.now()),
                new Account("hirwa", "test$123", "vanessa@test.com", "Vanessa" ,"Hirwa","Dairy is her middle name (not really, but close)! Vanessa, a dairy consultant at Mulika farms, brings her expertise and love for quality ingredients to the table. ❤️","Farmer" ,"Mulika farms",LocalDateTime.now())
                ).forEach(accountService::saveAccount);

        System.out.println("Account data seeded successfully!");
    }
}
